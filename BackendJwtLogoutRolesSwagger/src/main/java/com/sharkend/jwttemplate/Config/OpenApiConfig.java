package com.sharkend.jwttemplate.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

//--- Official Documentation: https://springdoc.org/ ---//
@Configuration
@OpenAPIDefinition(
    info = @Info(
            contact = @Contact(
                    name = "sharkend",
                    email = "john.doe@example.donotsendmailhere",
                    url = "https://github.com/sharkend"
            ),
            description = "Description for big bold title",
            title = "big bold title",
            version = "version here"
            //license, TOS
    ),
    servers = {
            @Server(
                    description = "server description (DEV)",
                    url = "http://localhost:9000"
            ),
            @Server(
                    description = "another server desc (PROD)",
                    url = "http://localhost:3000"
            ),
    },
    security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP, //change for OAuth
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
