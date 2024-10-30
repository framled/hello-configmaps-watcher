package com.hello.helloworldservice.api;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${openapi.holaMundo.base-path:/v1}")
public class HolaApiController implements HolaApi {
  private final MeterRegistry registry;
  private Timer holaMundoTimer;

  private final HolaApiDelegate delegate;

  public HolaApiController(@org.springframework.beans.factory.annotation.Autowired(
      required = false) HolaApiDelegate delegate, MeterRegistry registry) {
    this.delegate = Optional.ofNullable(delegate).orElse(new HolaApiDelegate() {});
    this.registry = registry;
    holaMundoTimer = registry.timer("holaMundoTimer.http_requests", "method", "GET", "api.name",
        "holaMundoTimer");
  }

  @Override
  public HolaApiDelegate getDelegate() {
    return delegate;
  }
}
