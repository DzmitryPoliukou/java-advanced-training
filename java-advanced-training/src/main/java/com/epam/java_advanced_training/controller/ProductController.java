package com.epam.java_advanced_training.controller;

import com.epam.java_advanced_training.dto.ProductRequestDto;
import com.epam.java_advanced_training.dto.ProductResponseDto;
import com.epam.java_advanced_training.entity.Product;
import com.epam.java_advanced_training.mapper.ProductMapper;
import com.epam.java_advanced_training.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ProductMapper productMapper;


  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
    Product product = productMapper.toEntity(productRequestDto);
    Product savedProduct = productService.createProduct(product);
    return productMapper.toDto(savedProduct);
  }


  @GetMapping("/{id}")
  public ProductResponseDto getProductById(@PathVariable Long id) {
    Product product = productService.getProductById(id);
    return productMapper.toDto(product);
  }

  @GetMapping
  public List<ProductResponseDto> getAllProducts() {
    List<Product> products = productService.getAllProducts();
    return productMapper.toDtoList(products);
  }

  @PutMapping("/{id}")
  public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
    Product product = productMapper.toEntity(productRequestDto);
    Product updatedProduct = productService.updateProduct(id, product);
    return productMapper.toDto(updatedProduct);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
  }
}
