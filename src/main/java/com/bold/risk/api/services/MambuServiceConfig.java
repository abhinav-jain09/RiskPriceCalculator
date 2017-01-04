package com.bold.risk.api.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bold.risk.api.properties.MambuConfigurationProperties;
import com.mambu.apisdk.MambuAPIServiceFactory;

public abstract class MambuServiceConfig {

	protected  MambuConfigurationProperties mambuConfigurationProperties;

	protected  MambuAPIServiceFactory mambuAPIServiceFactory;

	@Autowired
	public MambuServiceConfig( MambuConfigurationProperties mambuConfigurationProperties,
			MambuAPIServiceFactory mambuAPIServiceFactory) {
		this.mambuConfigurationProperties = mambuConfigurationProperties;
		this.mambuAPIServiceFactory = mambuAPIServiceFactory;
	}
}
