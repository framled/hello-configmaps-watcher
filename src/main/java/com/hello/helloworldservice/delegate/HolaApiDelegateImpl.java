package com.hello.helloworldservice.delegate;

import com.hello.helloworldservice.api.HolaApi;
import com.hello.helloworldservice.api.HolaApiController;
import com.hello.helloworldservice.api.HolaApiDelegate;
import com.hello.helloworldservice.config.FeatureFlags;
import com.hello.helloworldservice.model.HelloWorldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * A delegate to be called by the {@link HolaApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
@Component
public class HolaApiDelegateImpl implements HolaApiDelegate {

  private final FeatureFlags featureFlags;

  @Autowired
  public HolaApiDelegateImpl(FeatureFlags featureFlags) {
    this.featureFlags = featureFlags;
  }

  /**
   * @see HolaApi#holaMundo
   */
  @Override
  public ResponseEntity<HelloWorldMessage> holaMundo() {
    HelloWorldMessage result = new HelloWorldMessage();

    if (featureFlags.isNewFeatureEnabled()) {
      result.message("Hola Sexy Mundo");
    } else {
      result.message("Hola Mundo");
    }

    return new ResponseEntity<HelloWorldMessage>(result, HttpStatus.valueOf(200));
  }
}
