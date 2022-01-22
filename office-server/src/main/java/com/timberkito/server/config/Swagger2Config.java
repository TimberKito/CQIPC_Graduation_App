package com.timberkito.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

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
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描controller包路径
                .apis(RequestHandlerSelectors.basePackage("com.timberkito.server.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("毕业设计后端系统接口文档")
                .description("企业办公信息化管理系统接口文档")
                .contact(new Contact("Timber.Wang","http:localhost:8081/doc.html","timberkitowang@gmail.com"))
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes(){
        // 设置请求头信息
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization","Authorization","Header");
        result.add(apiKey);
        return result;
    }

    private List<springfox.documentation.spi.service.contexts.SecurityContext> securityContexts(){
        List<springfox.documentation.spi.service.contexts.SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/hello/.*"));
        return result;
    }

    private springfox.documentation.spi.service.contexts.SecurityContext getContextByPath(String pathRegex){
        return springfox.documentation.spi.service.contexts.SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }

}
