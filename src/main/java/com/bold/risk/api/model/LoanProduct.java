package com.bold.risk.api.model;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanProduct {

    @ApiModelProperty(position = 1, required = true, value = "Id of the Asset type", readOnly = true)
    private String id;

    @ApiModelProperty(position = 2, required = true, value = "Name string of the Asset ", readOnly = true, example = "Operational assets")
    private String name;

    @ApiModelProperty(position = 3, required = true, value = "Minimum Amount of the Asset ", readOnly = true, example = "50000")
    private BigDecimal minLoanAmount;

    @ApiModelProperty(position = 4, required = true, value = "Maximum Amount of the Asset ", readOnly = true, example = "1000000")
    private BigDecimal maxLoanAmount;

    @ApiModelProperty(position = 5, required = true, value = "Minimum Duration of the Asset ", readOnly = true, example = "12")
    private int minNumInstallments;

    @ApiModelProperty(position = 6, required = true, value = "Maximum duration of the Asset ", readOnly = true, example = "48")
    private int maxNumInstallments;

}
