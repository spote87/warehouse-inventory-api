package com.example.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is main class which launches warehouse inventory app.
 *
 * @author Shivaji Pote
 */
@SpringBootApplication
public class WarehouseInventoryApplication {

  /**
   * Main method used by spring boot to launch application.
   *
   * @param args runtime arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(WarehouseInventoryApplication.class, args);
  }

}
