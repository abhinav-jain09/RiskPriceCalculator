package com.bold.risk.api.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bold.risk.api.model.LoanProduct;
import com.bold.risk.api.properties.MambuConfigurationProperties;
import com.mambu.apisdk.MambuAPIServiceFactory;
import com.mambu.apisdk.exception.MambuApiException;
import com.mambu.apisdk.services.LoansService;

@Component
public class FetchAssetTypesService extends MambuServiceConfig {


	public FetchAssetTypesService(MambuConfigurationProperties mambuConfigurationProperties,
			MambuAPIServiceFactory mambuAPIServiceFactory) {
		super(mambuConfigurationProperties, mambuAPIServiceFactory);
		// TODO Auto-generated constructor stub
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(FetchAssetTypesService.class);



	public List<LoanProduct> processGetAssetTypeRequest() throws MambuApiException {
		LoansService loanService = mambuAPIServiceFactory.getLoanService();

		return Arrays.stream(mambuConfigurationProperties.getLoanProductTypesArray())
				.map(lp -> {
					try {
						return loanService.getLoanProduct(lp);
					} catch (MambuApiException e) {
						throw new RuntimeException(e);
					}
				}).map(this::createAssetType)
				.collect(Collectors.toList());
	}

	private LoanProduct createAssetType(final com.mambu.loans.shared.model.LoanProduct loanproduct) {
		return LoanProduct.builder()
				.name(loanproduct.getName())
				.id(loanproduct.getId())
				.maxLoanAmount(loanproduct.getMaxLoanAmount().getAmount())
				.minLoanAmount(loanproduct.getMinLoanAmount().getAmount())
				//Min and Max duration is calculated based on the number of installment currently field set
				.minNumInstallments(loanproduct.getMinNumInstallments())
				.maxNumInstallments(loanproduct.getMaxNumInstallments())
				.build();
	}
}
