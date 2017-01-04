package com.bold.risk.api.loanproduct;

import com.bold.risk.api.model.LoanProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanProductControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testGetLoanProducts() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/loan-product")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo(objectMapper.writeValueAsString(
				Arrays.asList(
						LoanProduct.builder().id("EPT_TL").name("Equipment - Term Loan")
								.maxLoanAmount(BigDecimal.valueOf(1000000))
								.maxNumInstallments(60)
								.minLoanAmount(BigDecimal.valueOf(50000))
								.minNumInstallments(12)
								.build(),
						LoanProduct.builder().id("INV_TL").name("Inventory - Term Loan")
								.maxLoanAmount(BigDecimal.valueOf(1000000))
								.maxNumInstallments(48)
								.minLoanAmount(BigDecimal.valueOf(50000))
								.minNumInstallments(12)
								.build(),
						LoanProduct.builder().id("VHL_TL").name("Vehicle - Term Loan")
								.maxLoanAmount(BigDecimal.valueOf(1000000))
								.maxNumInstallments(60)
								.minLoanAmount(BigDecimal.valueOf(50000))
								.minNumInstallments(12)
								.build())))));
	}

}
