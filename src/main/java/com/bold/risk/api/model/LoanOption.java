package com.bold.risk.api.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanOption {
	private String rating;
	private BigDecimal installment;
	private BigDecimal interestRate;
}
