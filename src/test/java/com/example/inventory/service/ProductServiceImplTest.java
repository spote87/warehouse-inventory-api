package com.example.inventory.service;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.entity.ProductEntity;
import com.example.inventory.model.ProductDTO;
import com.example.inventory.model.ProductRequest;
import com.example.inventory.repository.ArticleRepository;
import com.example.inventory.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.example.inventory.utils.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Shivaji Pote
 **/
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl productService;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ArticleRepository articleRepository;

  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  void saveProducts_SavesCallsRespectiveRepositoryMethodsToSaveProductAndArticles() throws IOException {
    doReturn(Optional.of(mockedArticleEntity(1l))).when(articleRepository).findById(anyLong());
    productService.saveProducts(getProducts());
    verify(productRepository, times(1)).saveAll(anyCollection());
    verify(articleRepository, times(6)).findById(anyLong());
  }

  @Test
  void saveProducts_ThrowsExceptionWhenArticleDoesNotExistInDatabase() throws IOException {
    doReturn(Optional.empty()).when(articleRepository).findById(anyLong());
    assertThrows(ResponseStatusException.class, () -> productService.saveProducts(getProducts()));
  }

  @Test
  void getAll_ReturnsListOfAllProductsWithAvailableArticlesInStocks() {
    when(productRepository.findAll()).thenReturn(mockedProductEntities());
    final List<ProductDTO> products = productService.getAllProducts();
    assertNotNull(products);
    assertEquals(1, products.size());
    assertNotNull(products.get(0).getArticles());
    assertEquals(2, products.get(0).getArticles().size());
  }

  @Test
  void saleProduct_RemovesProductFromDatabaseAndUpdatesInventoryAccordingly() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockedProductEntities().iterator().next()));
    final Optional<ArticleEntity> mockedArt1 = Optional.of(mockedArticleEntities().iterator().next());
    when(articleRepository.findById(eq(1l))).thenReturn(mockedArt1);
    final Optional<ArticleEntity> mockedArt2 = Optional.of(mockedArticleEntities().iterator().next());
    when(articleRepository.findById(2l)).thenReturn(mockedArt2);
    productService.saleProduct(1);
    verify(productRepository, times(1)).findById(eq(1l));
    verify(articleRepository, times(1)).findById(eq(1l));
    verify(articleRepository, times(1)).findById(eq(2l));
    verify(articleRepository, times(1)).saveAll(anyCollection());
    verify(productRepository, times(1)).delete(any(ProductEntity.class));
  }

  @Test
  void saleProduct_ThrowsExceptionWhenProductDoesNotExistInDatabase() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class, () -> productService.saleProduct(1l));
  }

  @Test
  void saleProduct_ThrowsExceptionWhenNotEnoughArticlesExistsInInventory() {
    final List<ProductEntity> mockedProducts = (List<ProductEntity>) mockedProductEntities();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockedProducts.get(0)));
    final ArticleEntity articleEntity = mockedArticleEntities().iterator().next();
    articleEntity.setAvailableStock(0);
    final Optional<ArticleEntity> mockedArt1 = Optional.of(articleEntity);
    when(articleRepository.findById(anyLong())).thenReturn(mockedArt1);
    assertThrows(ResponseStatusException.class, () -> productService.saleProduct(1));
  }

  @Test
  void saleProduct_ThrowsExceptionWhenArticleDoesNotExistInInventory() {
    final List<ProductEntity> mockedProducts = (List<ProductEntity>) mockedProductEntities();
    when(productRepository.findById(anyLong())).thenReturn(Optional.of(mockedProducts.get(0)));
    when(articleRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class, () -> productService.saleProduct(1));
  }

  private ProductRequest getProducts() throws IOException {
    return mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("products.json"), ProductRequest.class);
  }
}
