package com.example.inventory.controller;

import com.example.inventory.model.ProductDTO;
import com.example.inventory.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Shivaji Pote
 **/
@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

  @MockBean
  private ProductService productService;

  @Autowired
  private MockMvc mockMvc;

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  void getAllProducts_ReturnsProductsWithStatusOK() throws Exception {
    final List<ProductDTO> mockedProducts = mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("all-products-response.json"), new TypeReference<>() {
    });
    when(productService.getAllProducts()).thenReturn(mockedProducts);
    mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string(mapper.writeValueAsString(mockedProducts)));
  }

  @Test
  void getAllProducts_CallsServiceMethodToSaleProduct() throws Exception {
    mockMvc.perform(delete("/product/1")).andExpect(status().isOk()).andExpect(content().string("Product sold!!"));
    verify(productService, times(1)).saleProduct(eq(1L));
  }

}
