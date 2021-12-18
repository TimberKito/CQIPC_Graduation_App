package com.timberkito.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Config Class
 *
 * @author Timber.Wang
 * @date 2021-12-18 7:31 PM
 */
@Configuration
@EnableSwagger2
public class Swagger2Config{

    @Bean
    public Docket createRestApi (){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.timberkito.server.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo (){
        return new ApiInfoBuilder()
                .title("CQIPC_Timber后端系统接口文档")
                .description("网上云办公系统后端接口文档")
                .contact(new Contact("Timber.Wang", "http:localhost:8081/doc.html", "timberkitowang@gmail.com"))
                .version("1.0")
                .build();
    }

}
