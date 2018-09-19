/**
 * 
 */
package net.fashiongo.webadmin.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
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
        this.listDocketParameters.add(oAuthHeader);
    }

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(listDocketParameters) //Your global required parameters and headers
				.select()
				.apis(RequestHandlerSelectors.basePackage("net.fashiongo.webadmin.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
