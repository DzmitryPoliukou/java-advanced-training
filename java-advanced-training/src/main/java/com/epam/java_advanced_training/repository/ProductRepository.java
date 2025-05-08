package com.epam.java_advanced_training.repository;

import com.epam.java_advanced_training.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
