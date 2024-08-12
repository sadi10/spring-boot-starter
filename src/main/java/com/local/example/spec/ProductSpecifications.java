package com.local.example.spec;

import org.springframework.data.jpa.domain.Specification;
import com.local.example.entity.Product;

public class ProductSpecifications {

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> hasId(Long id) {
        return (root, query, criteriaBuilder) ->
                id == null ? null : criteriaBuilder.equal(root.get("id"), id);
    }

    // Add more methods for other fields if needed
}
