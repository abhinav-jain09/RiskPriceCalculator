package com.bold.risk.api.messages;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Indicative Quote Request")
public class IndicativeLoanRequest {

	@Min(value = 50000, message = "minimum")
	@Max(value = 1000000, message = "maximum")
	@JsonProperty(value = "amount")
	@ApiModelProperty(position = 1, required = true, value = "Amount for the Loan", readOnly = true, example = "100000")
	private BigDecimal amount;

	@JsonProperty(value = "type")
	@ApiModelProperty(position = 2, required = true, value = "Id of the Asset type", readOnly = true, example = "EPT_TL")
	private String type;

	@JsonProperty(value = "value")
	@ApiModelProperty(position = 3, required = true, value = "value of Asset type", readOnly = true, example = "90000")
	private BigDecimal value;

}
