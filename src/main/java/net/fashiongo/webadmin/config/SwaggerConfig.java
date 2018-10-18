/**
 * 
 */
package net.fashiongo.webadmin.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author kcha
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private List<Parameter> listDocketParameters;
	
	public SwaggerConfig() {
        
        //Any parameter or header you want to require for all end_points
        Parameter oAuthHeader = new ParameterBuilder()
                                    .name("Authorization")
                                    .description("OAUTH JWT Bearer Token")
                                    .defaultValue("Bearer {JWT Token}")
                                    .modelRef(new ModelRef("string"))
                                    .parameterType("header")
                                    .build();
    
        this.listDocketParameters = new ArrayList<Parameter>();
//        this.listDocketParameters.add(oAuthHeader);
    }

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(listDocketParameters) //Your global required parameters and headers
				.select()
				.apis(RequestHandlerSelectors.basePackage("net.fashiongo.webadmin.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.protocols(protocols())
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("WebAdmin-API Swagger Documentation")
				.description("ELambs Integration API")
				.build();
	}
	
	private Set<String> protocols() {
		Set<String> protocols = new HashSet<>();
		protocols.add("http");
		return protocols;
	}
	
	private List<? extends SecurityScheme> securitySchemes() {
        List<SecurityScheme> authorizationTypes = Arrays.asList(new ApiKey("Authorization", "Authorization", "header"));
        return authorizationTypes;
    }
	
	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts   = Arrays.asList(SecurityContext.builder().forPaths(PathSelectors.any()).securityReferences(securityReferences()).build());
		return securityContexts;
	}
	
	private List<SecurityReference> securityReferences() {
		List<SecurityReference> securityReferences = Arrays.asList(SecurityReference.builder().reference("Authorization").scopes(new AuthorizationScope[0]).build());
		return securityReferences;
	}
}
