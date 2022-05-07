package com.tiberiumaxim.demoaplicatietema02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Tiberiu Maxim", "https://github.com/MaximTiberiu", "tiberiu.maxim@s.unibuc.ro");

        return new ApiInfo(
                "Demo Shop API",
                "MDS - Homework #02",
                "1.0",
                "localhost:8080",
                contact,
                "Apache License Version 2.0",
                "https://github.com/MaximTiberiu/DemoApp---MDS---Tema02/blob/main/LICENSE",
                new ArrayList<>()
        );
    }
}
