package com.minhgh.employee.service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Api Specification - MinhGH",
                description = "Api documentation for Employee service",
                version = "1.0",
                contact = @Contact(
                        name = "MinhGH",
                        email = "minhgh@gmail.com",
                        url = "https://minhgh.vercel.app"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://minhgh.vercel.app/licences"
                ),
                termsOfService = "https://minhgh.vercel.app/terms"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:9002"
                ),
                @Server(
                        description = "Dev ENV",
                        url = "https://employee-service.dev.com"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "https://employee-service.prod.com"
                )
        }
)
public class OpenApiConfig {
}
