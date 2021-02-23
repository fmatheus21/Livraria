package com.fmatheus.app.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fmatheus.app.controller.resource"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, this.responseMessageForGET())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("REST API")
                .description("REST API de um sistema de Gerenciamento de Equipamento de Informática")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Fernando Matheus", "https://github.com/fmatheus21", "fernando.matheuss@outlook.com"))
                .build();
    }

    @SuppressWarnings("serial")
    private List<ResponseMessage> responseMessageForGET() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(500)
                        .message("500 message")
                        .responseModel(new ModelRef("Error"))
                        .build());
                add(new ResponseMessageBuilder()
                        .code(403)
                        .message("Forbidden!")
                        .build());
            }
        };
    }

}
