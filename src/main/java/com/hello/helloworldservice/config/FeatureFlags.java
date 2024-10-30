package com.hello.helloworldservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "features")
@Getter
@Setter(AccessLevel.PACKAGE)
public class FeatureFlags {
  private boolean newFeatureEnabled;
}
