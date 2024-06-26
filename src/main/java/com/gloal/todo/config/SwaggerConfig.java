package com.gloal.todo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myOpenApi() {

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        Contact contact = new Contact();
        contact.setUrl("https://github.com/Gloal/");

        Info appInfo = new Info().title("Todo Api")
                .version("1.0").contact(contact)
                .description("Todo App Api Documentation")
                .termsOfService("FreeToUSe")
                .license(mitLicense);

        return new OpenAPI().info(appInfo);
    }

}
