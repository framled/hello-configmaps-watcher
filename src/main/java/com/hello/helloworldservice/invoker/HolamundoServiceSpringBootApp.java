package com.hello.helloworldservice.invoker;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {
    SecurityAutoConfiguration.class,
    UserDetailsServiceAutoConfiguration.class,
    WebMvcAutoConfiguration.class
})
@EnableConfigurationProperties
@EnableWebMvc
@ComponentScan(basePackages = {
    "com.hello.helloworldservice.invoker",
    "com.hello.helloworldservice.api",
    "com.hello.helloworldservice.config",
    "com.hello.helloworldservice.delegate",
    "com.hello.helloworldservice.monitoring"
})
public class HolamundoServiceSpringBootApp implements CommandLineRunner {

  @Override
  public void run(String... arg0) throws Exception {
    if (arg0.length > 0 && arg0[0].equals("exitcode")) {
      throw new ExitException();
    }
  }

  /** This is generated from the new version */
  public static void main(String[] args) throws Exception {
    new SpringApplication(HolamundoServiceSpringBootApp.class).run(args);
  }

  static class ExitException extends RuntimeException implements ExitCodeGenerator {
    private static final long serialVersionUID = 1L;

    @Override
    public int getExitCode() {
      return 10;
    }
  }

  @Bean
  public WebMvcConfigurer webConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
      }

      @Override
      public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/3.14.2/");
      }
    };
  }

  @Bean
  static public PropertySourcesPlaceholderConfigurer properties() {
    final PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
    ppc.setIgnoreResourceNotFound(true);

    final List<Resource> resourceLst = new ArrayList<>();

    resourceLst.add(new ClassPathResource("application.properties"));
    resourceLst.add(new ClassPathResource("helloService.properties"));

    ppc.setLocations(resourceLst.toArray(new Resource[] {}));

    return ppc;
  }
}