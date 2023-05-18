package com.hust.nhakhoa.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Nha khoa API",
                version = "1.0.0",
                description = "Nha khoa API",
                termsOfService = "runcode",
                contact = @Contact(
                        name = "Hung",
                        email = "doquanghung204@gmail.com"
                )
        )

)

public class OpenApiConfig {
}
