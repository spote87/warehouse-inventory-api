package com.example.inventory.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

/**
 * Context class which calls respective strategy.
 *
 * @author Shivaji Pote
 **/
@RequiredArgsConstructor
@Log4j2
public class DataLoaderContext {

  private final DataLoaderStrategy dataLoaderStrategy;

  /**
   * This method executes {@link DataLoaderStrategy} from this context.
   *
   * @param inputStream stream of data to be passed to strategy
   * @throws IOException this exception is thrown by strategy class which is redirected to calling class
   */
  public void executeStrategy(final InputStream inputStream) throws IOException {
    dataLoaderStrategy.load(inputStream);
  }
}
