package com.marvisa.logistic.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI logisticOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Marvisa Logistic API")
                        .description(
                                "Spring Boot 4.0 for logistics publishing via REST endpoints")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Ing. Oscar Meneses Yaranga")
                                .url("https://github.com/oscarmy333/")
                                .email("oscar.meya@gmail.com"))
                        .license(new License().name("MIT").url("https://spdx.org/licenses/MIT.html")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/public/**")
                .build();
    }

}