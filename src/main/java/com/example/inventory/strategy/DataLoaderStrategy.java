package com.example.inventory.strategy;

import java.io.IOException;
import java.io.InputStream;

/**
 * Strategy interface for loading data in warehouse inventory.
 *
 * @author Shivaji Pote
 **/
public interface DataLoaderStrategy {

  /**
   * This method loads inventory data in database.
   *
   * @param data {@link InputStream} containing data to be loaded in database
   * @throws IOException if fails to read data from specified input stream
   */
  void load(InputStream data) throws IOException;
}
