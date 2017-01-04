package com.bold.risk.api.loadproduct;

import com.bold.risk.api.model.LoanProduct;
import com.bold.risk.api.services.FetchAssetTypesService;
import com.mambu.apisdk.exception.MambuApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Risk and pricing engine handlers")
@RestController
@RequestMapping("/api/loan-product")
public class LoanProductController {
	@Autowired
	FetchAssetTypesService fetchAssetTypesService;

	@CrossOrigin
	@ApiOperation(value = "This api returns all the LoanProducts and its attributes.")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<LoanProduct> getLoanProduct() throws MambuApiException {
		return fetchAssetTypesService.processGetAssetTypeRequest();
	}
}
