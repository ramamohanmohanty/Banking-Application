package org.jt.BankingManagementSystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    OpenAPI openAPI(){
        return new OpenAPI().info(getInfo());
    }

    private Info getInfo() {
        Info info = new Info();
        info.setTitle("Banking System");
        info.setDescription("Management of Banking System");
        info.setVersion("v1");
        return info;
    }
}
