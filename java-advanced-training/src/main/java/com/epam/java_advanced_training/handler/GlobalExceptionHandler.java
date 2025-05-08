package com.epam.java_advanced_training.handler;

import com.epam.java_advanced_training.dto.ErrorMessage;
import com.epam.java_advanced_training.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorMessage handleProductNotFoundException(final ProductNotFoundException ex) {
    return new ErrorMessage(ex.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()));

  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorMessage handleGenericException(final Exception ex) {
    return new ErrorMessage(ex.getMessage(), String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
  }

}
