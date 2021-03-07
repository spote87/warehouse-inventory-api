package com.example.inventory.service;

import com.example.inventory.entity.ArticleEntity;
import com.example.inventory.entity.ProductArticleEntity;
import com.example.inventory.entity.ProductEntity;
import com.example.inventory.model.Article;
import com.example.inventory.model.ProductDTO;
import com.example.inventory.model.ProductRequest;
import com.example.inventory.repository.ArticleRepository;
import com.example.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Shivaji Pote
 **/
@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  private final ArticleRepository articleRepository;

  @Override
  public void saveProducts(final ProductRequest productRequest) {
    final Iterable<ProductEntity> products = productRequest.getProducts().stream().map(this::getProductEntity).collect(Collectors.toList());
    productRepository.saveAll(products);
  }

  @Override
  public List<ProductDTO> getAllProducts() {
    final List<ProductEntity> productEntities = (List<ProductEntity>) productRepository.findAll();
    return productEntities.stream().map(this::getProduct).collect(Collectors.toList());
  }

  @Override
  public void saleProduct(final long productId) {
    final ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in database"));
    final List<ArticleEntity> articleEntities = product.getProductArticleEntity().stream().map(this::removeArticle).collect(Collectors.toList());
    productRepository.delete(product);
    articleRepository.saveAll(articleEntities);
  }

  private ArticleEntity removeArticle(final ProductArticleEntity prdArtEntity) {
    final ArticleEntity articleEntity = getArticleEntity(prdArtEntity.getProductArticleKey().getArticleEntity().getArticleId());
    if (prdArtEntity.getQuantity() > articleEntity.getAvailableStock()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough stock available of article " + articleEntity.getArticleId());
    }
    articleEntity.setAvailableStock(articleEntity.getAvailableStock() - prdArtEntity.getQuantity());
    return articleEntity;
  }

  private ProductDTO getProduct(final ProductEntity productEntity) {
    final ProductDTO.ProductDTOBuilder builder = ProductDTO.builder();
    builder.name(productEntity.getName());
    builder.price(productEntity.getPrice());
    builder.productId(productEntity.getProductId());
    final List<Article> articles = productEntity.getProductArticleEntity().stream().map(prdArt -> InventoryHelper.getArticle(prdArt.getProductArticleKey().getArticleEntity())).collect(Collectors.toList());
    builder.articles(articles);
    return builder.build();
  }

  private ArticleEntity getArticleEntity(final long articleId) {
    return articleRepository.findById(articleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid article id " + articleId));
  }

  private ProductEntity getProductEntity(final com.example.inventory.model.Product product) {
    final ProductEntity entity = new ProductEntity();
    entity.setName(product.getName());
    final Set<ProductArticleEntity> prdArticles = new HashSet<>();
    product.getArticles().forEach(article -> {
      final ArticleEntity articleEntity = getArticleEntity(article.getArticleId());
      final ProductArticleEntity productArticleEntity = getProductArticles(entity, articleEntity, article);
      prdArticles.add(productArticleEntity);
    });
    entity.setProductArticleEntity(prdArticles);
    return entity;
  }

  private ProductArticleEntity getProductArticles(final ProductEntity entity, final ArticleEntity articleEntity, final com.example.inventory.model.ProductArticle article) {
    final ProductArticleEntity productArticleEntity = new ProductArticleEntity();
    final ProductArticleEntity.ProductArticleKey key = new ProductArticleEntity.ProductArticleKey();
    key.setProductEntity(entity);
    key.setArticleEntity(articleEntity);
    productArticleEntity.setProductArticleKey(key);
    productArticleEntity.setQuantity(article.getQuantity());
    return productArticleEntity;
  }

}
