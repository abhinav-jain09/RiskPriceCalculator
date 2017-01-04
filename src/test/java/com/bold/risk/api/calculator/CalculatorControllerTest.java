/*package com.bold.risk.api.calculator;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bold.risk.api.messages.IndicativeLoanRequest;
import com.bold.risk.api.model.ErrorMessage;
import com.bold.risk.api.model.IndicativeLoan;
import com.bold.risk.api.model.LoanOption;
import com.bold.risk.api.model.ValidationErrors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetIndicativeQuoteOk() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/calculator/indicative-quote")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(IndicativeLoanRequest.builder().amount(BigDecimal.valueOf(100000.00)).build()))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo(objectMapper.writeValueAsString(
				IndicativeLoan.builder()
				.options(Arrays.asList(
						LoanOption.builder().rating("CCC").interestRate(BigDecimal.valueOf(7.25)).installment(BigDecimal.valueOf(2186.26)).build(),
						LoanOption.builder().rating("BBB").interestRate(BigDecimal.valueOf(5.45)).installment(BigDecimal.valueOf(2124.35)).build(),
						LoanOption.builder().rating("AAA").interestRate(BigDecimal.valueOf(5.9)).installment(BigDecimal.valueOf( 2093.39)).build()
						))
				.status(true)
				.statusCode(1)
				.approvedAmount(BigDecimal.valueOf(81000))
				.duration(48)
				.minimumDuration(12)
				.maximumDuration(48)
				.build()))));
	}

	@Test
	public void testGetIndicativeQuoteAmountMissing() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/calculator/indicative-quote")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(IndicativeLoanRequest.builder().build()))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().string(equalTo(getAmountErrorResponse(true))));
	}

	@Test
	public void testGetIndicativeQuoteAmountMinimumExceeded() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/calculator/indicative-quote")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(IndicativeLoanRequest.builder().amount(BigDecimal.valueOf(49999)).build()))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().string(equalTo(getAmountErrorResponse(true))));
	}

	@Test
	public void testGetIndicativeQuoteAmountMaximumExceeded() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/calculator/indicative-quote")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(IndicativeLoanRequest.builder().amount(BigDecimal.valueOf(1000001)).build()))
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().string(equalTo(getAmountErrorResponse(false))));
	}

	private String getAmountErrorResponse(boolean minimum) throws JsonProcessingException {
		return objectMapper.writeValueAsString(ValidationErrors.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.reason(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.fieldErrors(ImmutableMap.<String, ErrorMessage>builder().put("amount",
						ErrorMessage.builder()
						.code("error." + (minimum ? "minimum" : "maximum"))
						.arguments(Arrays.asList(
								minimum ? 50000 : 1000000
								)).build()
						).build()).build());
	}

}
 */