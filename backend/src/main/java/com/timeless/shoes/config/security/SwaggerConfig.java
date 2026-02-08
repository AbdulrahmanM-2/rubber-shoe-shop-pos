package com.timeless.shoes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Main OpenAPI configuration
     */
    @Bean
    public OpenAPI timelessShoesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Timeless Shoes POS API")
                        .description("REST API for Timeless Shoes Point-of-Sale and Inventory System")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Timeless Shoes Dev Team")
                                .email("support@timelessshoes.com")
                                .url("https://timelessshoes.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }

    /**
     * Optional: group API endpoints
     */
    @Bean
    public GroupedOpenApi apiGroup() {
        return GroupedOpenApi.builder()
                .group("timeless-shoes")
                .pathsToMatch("/api/**")
                .build();
    }
}
