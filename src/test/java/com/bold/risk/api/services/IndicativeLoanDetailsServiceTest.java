package com.bold.risk.api.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.mambu.apisdk.exception.MambuApiException;

public class IndicativeLoanDetailsServiceTest {

	IndicativeLoanDetailsService indicativeLoanDetailsServiceImpl = new IndicativeLoanDetailsService(null, null);

	@Test
	public void calculateLoanDurationTest() {
		assertEquals(48.0, indicativeLoanDetailsServiceImpl.calcuateLoanDuration(), 0.0);
	}

	@Test
	public void calculateLGDTest() {
		assertEquals(0.45, indicativeLoanDetailsServiceImpl.calculateLGD().doubleValue(), 0.0);
	}

	@Test
	public void calculateLoanAmountTest() {
		assertEquals(81000.0, indicativeLoanDetailsServiceImpl.calculateLoanAmount(BigDecimal.valueOf(100000.00),BigDecimal.valueOf( 90000.00)).doubleValue(), 0.0);
	}

	@Test
	public void calculatePdTest() {
		assertEquals(5.00, indicativeLoanDetailsServiceImpl.calculatePd(BigDecimal.valueOf(5.00)).doubleValue(), 0.0);
	}

	@Test
	public void riskPremiumTest() {
		assertEquals(2.25, indicativeLoanDetailsServiceImpl.riskPremium(BigDecimal.valueOf(5.00)).doubleValue(), 0.0);
	}

	@Test
	public void calculateInterestRateTest() {
		assertEquals(7.25, indicativeLoanDetailsServiceImpl.calculateInterestRate(BigDecimal.valueOf(5.00)).doubleValue(), 0.0);
	}

	@Test
	public void calculateInstalmentTest() throws MambuApiException {
		//assertEquals(BigDecimal.valueOf(2040.1875), indicativeLoanDetailsServiceImpl.calculateInstallemnt(100000.00, 90000.00, 0.5), 0.0);

		//	System.out.println("this is output " +indicativeLoanDetailsServiceImpl.calculateInstallemnt(100000.00, 90000.00, 0.5));
	}
}
