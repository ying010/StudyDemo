package com.wzy.study.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * swagger配置文件
 * @author 王志英
 */
@Configuration
@ComponentScan(basePackages="com.wzy.study.*.controller")
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(swaggerDemoApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(allowPaths())
                .build();
    }

    @Bean
    public UiConfiguration uiConfiguration() {
        return UiConfigurationBuilder.builder()
                .filter(true)
                .build();
    }

    private ApiInfo swaggerDemoApiInfo() {
        Contact contact = new Contact("测试", "", "");
        return new ApiInfoBuilder()
                .contact(contact)
                .title("Swagger2构建API文档")
                .description("构建文档")
                .version("1.0.0")
                .build();
    }

    private Predicate<String> allowPaths() {
        return regex("/api.*").or(regex("/boot.*"));
//        return url -> url.matches("/api.*") || url.matches("/boot.*");

    }
}
