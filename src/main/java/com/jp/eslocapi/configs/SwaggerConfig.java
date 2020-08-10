package com.jp.eslocapi.configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
					.useDefaultResponseMessages(false)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.jp.eslocapi.api.resources"))
					.paths(PathSelectors.any())
					.build()
					.securityContexts(Arrays.asList(securityContext()))
					.securitySchemes(Arrays.asList(apiKey()))
					.apiInfo(apiInfo())
					;
	}
	
	private Contact contact() {
		return new Contact("João Paulo Santana Gusmão", 
				"https://github.com/teoitinga/manager-esloc.git", 
				"joao.gusmao@emater.mg.gov.br");
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("esloc-api")
				.description("API de Gestão de atendimentos no esloc")
				.version("1.0")
				.contact(contact())
				.build();
	}
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    public ApiKey apiKey() {
    	return new ApiKey("JWT", "Authorization", "header");
    }
    
    public List<SecurityReference> defaultAuth(){
    	AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverythinga");
    	
    	AuthorizationScope[] scopes = new AuthorizationScope[1];
    	scopes[0] = authorizationScope;
    	SecurityReference reference = new SecurityReference("JWT", scopes);
    	List<SecurityReference> auths = new ArrayList<>();
    	auths.add(reference);
    	return auths;
    }
    private SecurityContext securityContext() {
    	return SecurityContext.builder()
    			.securityReferences(defaultAuth())
    			.forPaths(PathSelectors.any())
    			.build();
    }
}
