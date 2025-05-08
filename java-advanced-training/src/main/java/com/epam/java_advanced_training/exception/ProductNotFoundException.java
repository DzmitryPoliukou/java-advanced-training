package com.epam.java_advanced_training.exception;

public class ProductNotFoundException extends RuntimeException {

  private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product with ID: [%s] not found.";

  public ProductNotFoundException(final String productId) {
    super(String.format(PRODUCT_NOT_FOUND_MESSAGE, productId));
  }
}
