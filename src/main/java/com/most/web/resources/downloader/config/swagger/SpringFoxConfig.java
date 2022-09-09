package com.most.web.resources.downloader.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("WebResourceDownloader Service")
                        .description("WebResourceDownloader Service")
                        .version("0.0.1-SNAPSHOT")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
