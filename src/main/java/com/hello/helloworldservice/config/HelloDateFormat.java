package com.hello.helloworldservice.config;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.text.FieldPosition;
import java.util.Date;

public class HelloDateFormat extends StdDateFormat {

  private static final long serialVersionUID = 1L;

  @Override
  public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
    return super.format(date, toAppendTo, fieldPosition);
  }
}
