package com.example.inventory.controller;

import com.example.inventory.strategy.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Shivaji Pote
 **/
@SpringBootTest
@AutoConfigureMockMvc
class DataLoaderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DataLoaderStrategyFactory strategyFactory;

  @MockBean
  private DataLoaderContext dataLoaderContext;

  @Test
  void loadData_CallsProductsStrategyWhenPassedFileNameIsProducts() throws Exception {
    final MockMultipartFile file = new MockMultipartFile("file", "products.json", APPLICATION_JSON_VALUE, Thread.currentThread().getContextClassLoader().getResourceAsStream("products.json").readAllBytes());
    final DataLoaderStrategy mockStrategy = mock(ProductLoaderStrategy.class);
    when(strategyFactory.getStrategy(eq("products"))).thenReturn(mockStrategy);
    mockMvc.perform(MockMvcRequestBuilders.multipart("/load").file(file)).andExpect(status().isOk());
    verify(strategyFactory, times(1)).getStrategy(eq("products"));
  }

  @Test
  void loadData_CallsArticleStrategyWhenPassedFileNameIsInventory() throws Exception {
    final MockMultipartFile file = new MockMultipartFile("file", "inventory.json", APPLICATION_JSON_VALUE, Thread.currentThread().getContextClassLoader().getResourceAsStream("inventory.json").readAllBytes());
    final DataLoaderStrategy mockStrategy = mock(ArticleLoaderStrategy.class);
    when(strategyFactory.getStrategy(eq("inventory"))).thenReturn(mockStrategy);
    mockMvc.perform(MockMvcRequestBuilders.multipart("/load").file(file)).andExpect(status().isOk());
    verify(strategyFactory, times(1)).getStrategy(eq("inventory"));
  }

  @Test
  void loadData_ThrowsExceptionWhenFileNameIsInvalid() throws Exception {
    final MockMultipartFile file = new MockMultipartFile("file", "invalid.json", APPLICATION_JSON_VALUE, Thread.currentThread().getContextClassLoader().getResourceAsStream("inventory.json").readAllBytes());
    when(strategyFactory.getStrategy(eq("invalid"))).thenReturn(null);
    mockMvc.perform(MockMvcRequestBuilders.multipart("/load").file(file)).andExpect(status().isBadRequest());
    verify(strategyFactory, times(0)).getStrategy(eq("inventory"));
  }

  @Test
  void loadData_ThrowsExceptionWhenInvalidFileExtensionIsPassed() throws Exception {
    final MockMultipartFile file = new MockMultipartFile("file", "invalid.txt", APPLICATION_JSON_VALUE, "invalid data".getBytes(StandardCharsets.UTF_8));
    mockMvc.perform(MockMvcRequestBuilders.multipart("/load").file(file)).andExpect(status().isBadRequest());
  }

}
