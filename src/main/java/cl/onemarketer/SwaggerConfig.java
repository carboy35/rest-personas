package cl.onemarketer;


import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .paths(PathSelectors.any())            
          .apis( RequestHandlerSelectors.basePackage("cl.onemarketer"))
          .build().pathMapping("/")
          .apiInfo(apiInfo());
         
    }
	
    
    private ApiInfo apiInfo() 
    {
        return new ApiInfo(
          "Personas REST API", 
          "Servicios de consulta y categorizacion de actividad", 
          "1.0", 
          "Terminos de servicio", 
          new Contact("ADEXUS", "", "ao.support@adexus.cl"), 
          "License of API", "API license URL", 
          Collections.emptyList());
   }
//	@Bean
//	public Docket postsApi() {
//		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
//				.apiInfo(apiInfo()).select().paths(postPaths()).build();
//	}
//
//	private Predicate<String> postPaths() {
//		return or(regex("/api/posts.*"), regex("/personas.*"));
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("JavaInUse API")
//				.description("JavaInUse API reference for developers")
//				.termsOfServiceUrl("http://javainuse.com")
//				.contact("javainuse@gmail.com").license("JavaInUse License")
//				.licenseUrl("javainuse@gmail.com").version("1.0").build();
//	}
}