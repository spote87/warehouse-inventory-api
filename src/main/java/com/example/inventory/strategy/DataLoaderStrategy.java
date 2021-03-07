package com.example.inventory.strategy;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shivaji Pote
 **/
public interface DataLoaderStrategy {

    void load(InputStream data) throws IOException;
}
