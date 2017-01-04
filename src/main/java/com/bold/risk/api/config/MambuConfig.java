package com.bold.risk.api.config;

import com.bold.risk.api.properties.MambuConfigurationProperties;
import com.mambu.apisdk.MambuAPIServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MambuConfig {
    private final MambuConfigurationProperties mambuConfigurationProperties;

    @Autowired
    public MambuConfig(MambuConfigurationProperties mambuConfigurationProperties) {
        this.mambuConfigurationProperties = mambuConfigurationProperties;
    }

    @Bean
    public MambuAPIServiceFactory createMambuAPIServiceFactory() {
        return MambuAPIServiceFactory.getFactory(
                mambuConfigurationProperties.getUrl(), mambuConfigurationProperties.getUsername(), mambuConfigurationProperties.getPassword());
    }
}
