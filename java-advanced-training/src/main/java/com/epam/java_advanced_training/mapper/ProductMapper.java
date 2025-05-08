package com.epam.java_advanced_training.mapper;

import com.epam.java_advanced_training.dto.ProductRequestDto;
import com.epam.java_advanced_training.dto.ProductResponseDto;
import com.epam.java_advanced_training.entity.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product toEntity(ProductRequestDto dto) {
    if (dto == null) {
      return null;
    }

    Product product = new Product();
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    return product;
  }

  public ProductResponseDto toDto(Product entity) {
    if (entity == null) {
      return null;
    }

    ProductResponseDto dto = new ProductResponseDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setPrice(entity.getPrice());
    return dto;
  }

  public List<ProductResponseDto> toDtoList(List<Product> entityList) {
    if (entityList == null || entityList.isEmpty()) {
      return new ArrayList<>();
    }

    List<ProductResponseDto> dtoList = new ArrayList<>();
    for (Product product : entityList) {
      dtoList.add(toDto(product));
    }
    return dtoList;
  }
}
