package com.example.inventory.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

/**
 * Exception handler class for warehouse inventory application.
 *
 * @author Shivaji Pote
 **/
@Log4j2
@ControllerAdvice
public class ExceptionHandler {

  /**
   * This method handles application wide {@link IOException}.
   *
   * @param e {@code IOException} instance
   */
  @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
  public void handleIOException(final IOException e) {
    log.error("Error occurred while loading data", e);
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to load inventory data", e);
  }
}
