package com.deepak.urlshortner.advice;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deepak.urlshortner.Exception.UrlNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UrlNotFoundException.class)
  public Map<String,String>  handleUrlNotFoundException (UrlNotFoundException e) {
    Map<String,String> response = Map.of("error", e.getMessage());
    return response;
  }
}
