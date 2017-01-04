package com.bold.risk.api.calculator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bold.risk.api.messages.IndicativeLoanRequest;
import com.bold.risk.api.model.IndicativeLoan;
import com.bold.risk.api.model.LoanOption;
import com.bold.risk.api.model.ValidationErrors;
import com.bold.risk.api.services.IndicativeLoanDetailsService;
import com.google.common.collect.ImmutableMap;
import com.mambu.apisdk.exception.MambuApiException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "Risk and pricing engine handlers")
@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

	@Autowired
	IndicativeLoanDetailsService indicativeLoanDetailsService;

	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Bad Request",response = ValidationErrors.class) })
	@CrossOrigin
	@ApiOperation(value = "This api returns indicative quote.")
	@RequestMapping(value = "indicative-quote", method = RequestMethod.POST, produces = "application/json")
	public IndicativeLoan getIndicativeQuote(
			@Validated @RequestBody IndicativeLoanRequest request) throws MethodArgumentNotValidException, MambuApiException, ParseException {


		List<LoanOption> loanOptions = getProbabilityOfDefaultMap().entrySet()
				.parallelStream()
				.map(pb -> LoanOption.builder()
						.installment(indicativeLoanDetailsService.calculateInstallemnt(request.getAmount(),request.getValue(),pb.getKey(),request.getType()))
						.interestRate(indicativeLoanDetailsService.calculateInterestRate(pb.getKey()))
						.rating(pb.getValue()).build()).collect(Collectors.toList());

		return IndicativeLoan.builder()
				.options(loanOptions)
				.approvedAmount(indicativeLoanDetailsService
						.calculateLoanAmount(request.getAmount(), request.getValue()))
				.status(true).statusCode(1)
				.maximumDuration(48)
				.minimumDuration(12)
				.duration(48)
				.build();
	}

	private ImmutableMap<BigDecimal, String> getProbabilityOfDefaultMap() {
		return ImmutableMap.<BigDecimal, String>builder()
				.put(BigDecimal.valueOf(5.0), "CCC")
				.put(BigDecimal.valueOf(1.0), "BBB")
				.put(BigDecimal.valueOf(0.5), "AAA")
				.build();
	}
}
