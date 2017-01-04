package com.bold.risk.api.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bold.risk.api.properties.MambuConfigurationProperties;
import com.mambu.apisdk.MambuAPIServiceFactory;
import com.mambu.apisdk.exception.MambuApiException;
import com.mambu.apisdk.services.LoansService;
import com.mambu.core.shared.model.Money;
import com.mambu.loans.shared.model.DisbursementDetails;
import com.mambu.loans.shared.model.LoanAccount;

@Component
public class IndicativeLoanDetailsService extends  MambuServiceConfig   {


	public IndicativeLoanDetailsService(MambuConfigurationProperties mambuConfigurationProperties,
			MambuAPIServiceFactory mambuAPIServiceFactory) {
		super(mambuConfigurationProperties, mambuAPIServiceFactory);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	MambuAPIServiceFactory mambuAPIServiceFactory;
	public double calcuateLoanDuration() {
		return 48;
	}

	public BigDecimal calculateLGD() {
		return BigDecimal.valueOf(0.45);
	}

	public BigDecimal calculateLoanAmount(BigDecimal enteredAmount, BigDecimal enteredValue) {
		return BigDecimal.valueOf(Math.min(enteredAmount.doubleValue(), .90 * enteredValue.doubleValue())) ;
	}

	public BigDecimal calculatePd(BigDecimal pd) {
		return pd;
	}

	public BigDecimal riskPremium(BigDecimal pd) {
		return calculateLGD().multiply( calculatePd(pd)) ;
	}

	public BigDecimal calculateInterestRate(BigDecimal pd) {
		return (riskPremium(pd).add(BigDecimal.valueOf(5)));
	}

	public BigDecimal calculateInstallemnt(BigDecimal amount, BigDecimal value, BigDecimal pd, String productId)  {



		return  getIndicativeFirstInstallement( amount,  value,  pd,productId );

	}


	private BigDecimal getIndicativeFirstInstallement(BigDecimal amount, BigDecimal value, BigDecimal pd, String productId) {


		BigDecimal firstInstallment = null;
		try {
			LoansService loanService = mambuAPIServiceFactory.getLoanService();
			LoanAccount account = new LoanAccount();
			account.setLoanAmount(calculateLoanAmount(amount , value));
			account.setInterestRate(calculateInterestRate(pd));
			DisbursementDetails ddetail = new DisbursementDetails();
			@SuppressWarnings("deprecation")
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String dateInString = "20/12/2016";
			Date  date = sdf.parse(dateInString);
			ddetail.setExpectedDisbursementDate(date);
			ddetail.setDisbursementDate(date);
			account.setDisbursementDetails(ddetail);
			account.setExpectedDisbursementDate(date);
			account.setGracePeriod(null);
			account.setRepaymentInstallments(null);
			account.setPrincipalRepaymentInterval(null);
			Money MoneyNull = null;
			BigDecimal BigDecimalNull = null ;
			account.setPeriodicPayment(MoneyNull);
			account.setPeriodicPayment(BigDecimalNull);

			firstInstallment = loanService.getLoanProductSchedule(productId, account).get(0).getPrincipalDue().getAmount().add(loanService.getLoanProductSchedule(productId, account).get(0).getInterestDue().getAmount());

		}
		catch(MambuApiException | ParseException  ex) {

		}
		return firstInstallment ;


	}

}
