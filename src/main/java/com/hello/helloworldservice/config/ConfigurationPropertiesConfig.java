package com.hello.helloworldservice.config;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationPropertiesConfig {
  @Bean
  @RefreshScope
  FeatureFlags featureConfig() {
    return new FeatureFlags();
  }
}
