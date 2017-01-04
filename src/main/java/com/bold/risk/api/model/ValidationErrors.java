package com.bold.risk.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrors {

	@ApiModelProperty(position = 1, required = true, value = "request status", readOnly = true,example = "400" )
	private int status;

	@ApiModelProperty(position = 2, required = true, value = "reason for error", readOnly = true,example = "Bad Request" )
	private String reason;

	@ApiModelProperty(position = 2, required = true, value = "error descriptions by field", readOnly = true, example = "fieldErrors: { \"field\": { \"error.minimum\" }" )
	private Map<String, ErrorMessage> fieldErrors;
}
