package com.bold.risk.api.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndicativeLoan {
	private boolean status;
	private int statusCode;
	private BigDecimal approvedAmount;
	private int duration;
	private int minimumDuration;
	private int maximumDuration;
	private List<LoanOption> options;
}
