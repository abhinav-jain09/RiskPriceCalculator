package com.bold.risk.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mambu")
@Data
public class MambuConfigurationProperties {
    private String url;
    private String username;
    private String password;
    private String loanProductTypes;

    public String[] getLoanProductTypesArray() {
        return loanProductTypes.split("[,]");
    }
}
