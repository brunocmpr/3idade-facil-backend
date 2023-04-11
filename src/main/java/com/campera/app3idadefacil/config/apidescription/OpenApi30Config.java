package com.campera.app3idadefacil.config.apidescription;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/*
https://stackoverflow.com/a/66049031/12096971
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "3idade API", version = "v1"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApi30Config {

    //Support for Multipart form data request (e.g. API that takes @FormData as parameter)
    //https://github.com/springdoc/springdoc-openapi/issues/833
    @Bean
    public MappingJackson2HttpMessageConverter octetStreamJsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(
                new MediaType("application", "json"),
                new MediaType("application", "octet-stream")));
        return converter;
    }
}
