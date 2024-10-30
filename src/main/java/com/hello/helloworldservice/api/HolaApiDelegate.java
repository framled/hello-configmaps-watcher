package com.hello.helloworldservice.api;

import com.hello.helloworldservice.model.HelloWorldMessage;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * A delegate to be called by the {@link HolaApiController}}. Implement this interface with a
 * {@link org.springframework.stereotype.Service} annotated class.
 */
public interface HolaApiDelegate {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  /**
   * GET /hola : Hello World example Simple example service to say hello world
   *
   * @return Success (status code 200)
   * @see HolaApi#holaMundo
   */
  default ResponseEntity<HelloWorldMessage> holaMundo() {
    getRequest().ifPresent(request -> {
      for (MediaType mediaType : MediaType.parseMediaTypes(request.getHeader("Accept"))) {
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          String exampleString = "{ \"message\" : \"message\" }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }
    });
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
