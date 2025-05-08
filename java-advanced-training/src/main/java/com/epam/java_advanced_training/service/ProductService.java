package com.epam.java_advanced_training.service;

import com.epam.java_advanced_training.entity.Product;
import com.epam.java_advanced_training.exception.ProductNotFoundException;
import com.epam.java_advanced_training.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
  }

  public Product updateProduct(Long id, Product newProductData) {
    return productRepository.findById(id)
        .map(existingProduct -> {
          existingProduct.setName(newProductData.getName());
          existingProduct.setPrice(newProductData.getPrice());
          return productRepository.save(existingProduct);
        })
        .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

  }

  public void deleteProduct(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
    } else {
      throw new ProductNotFoundException("Product not found with id: " + id);
    }
  }
}
