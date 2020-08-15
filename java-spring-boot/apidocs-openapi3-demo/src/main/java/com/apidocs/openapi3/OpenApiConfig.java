package com.apidocs.openapi3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "${springdoc.definition.info.title}"
                , version = "${springdoc.definition.info.version}"
                , description = "${springdoc.definition.info.description}"
                , contact = @Contact(
                         name = "${springdoc.definition.info.contact.name}"
                        , url = "${springdoc.definition.info.contact.url}"
                        , email = "${springdoc.definition.info.contact.email}"
        )
        , license = @License(name = "Apache 2.0")))
public class OpenApiConfig {

//        @Bean
//        public GroupedOpenApi customerGroupedOpenApi() {
//               return GroupedOpenApi
//                        .builder()
//                        .setGroup("v1")
//                         .pathsToMatch("/api/v1/**")
//                        .packagesToScan("com.edtech.openapi3.apis")
//                        .build();
//        }
}
