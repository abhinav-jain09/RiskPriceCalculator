package com.bold.risk.api.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

	@ApiModelProperty(position = 1, required = true, value = "Error code in string can be used for translation", readOnly = true,example = "error.amount.maximum" )
	private String code;

	@ApiModelProperty(position = 2, required = true, value = "The arguments that are required", readOnly = true,example = "10000000" )
	private List<Object> arguments;
}
