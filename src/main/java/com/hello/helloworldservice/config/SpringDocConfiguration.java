package com.hello.helloworldservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

  @Bean
  OpenAPI apiInfo() {
    return new OpenAPI().info(new Info().title("HolaMundo")
      .description("Hello World example service.")
      .termsOfService("https://example.com/terms/")
      .contact(new Contact().email("example@example.com"))
      .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")).version("1.0.0"));
  }
}
