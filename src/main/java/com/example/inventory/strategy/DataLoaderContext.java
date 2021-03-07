package com.example.inventory.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shivaji Pote
 **/
@RequiredArgsConstructor
@Log4j2
public class DataLoaderContext {

  private final DataLoaderStrategy dataLoaderStrategy;

  public void executeStrategy(final InputStream inputStream) throws IOException {
    dataLoaderStrategy.load(inputStream);
  }
}
