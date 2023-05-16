package com.micro.demo.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe pour configurer api swagger et les endpoints affichés dans la doc
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
@Bean
    public Docket api() {
    ApiSelectorBuilder path = new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.micro.demo"))
            .paths(PathSelectors.any());

    return path.build();
}
}
