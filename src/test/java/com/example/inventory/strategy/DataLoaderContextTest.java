package com.example.inventory.strategy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Shivaji Pote
 **/
@ExtendWith(MockitoExtension.class)
class DataLoaderContextTest {

  @InjectMocks
  private DataLoaderContext dataLoaderContext;

  @Mock
  private DataLoaderStrategy dataLoaderStrategy;

  @Test
  void executeStrategy_CalsStrategyMethodWithWhenPassedValidDataStream() throws IOException {
    dataLoaderContext.executeStrategy(getTestResource());
    verify(dataLoaderStrategy, times(1)).load(any(InputStream.class));
  }

  @Test
  void executeStrategy_ThrowsResponseStatusExceptionWhenStrategyThrowsIOException() throws IOException {
    doThrow(IOException.class).when(dataLoaderStrategy).load(Mockito.any(InputStream.class));
    assertThrows(IOException.class, () -> dataLoaderContext.executeStrategy(getTestResource()));
  }

  private InputStream getTestResource() {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream("products.json");
  }

}
