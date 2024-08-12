package com.local.example.repository;

import com.local.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,Integer> , JpaSpecificationExecutor {
    Product findByName(String name);
}

