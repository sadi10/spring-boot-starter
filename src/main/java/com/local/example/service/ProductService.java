package com.local.example.service;

import com.local.example.entity.Product;
import com.local.example.model.CommonDomainModel;
import com.local.example.repository.ProductRepository;
import com.local.example.spec.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public CommonDomainModel<List<Product>> getProductsPaginated(int pageNo,
                                                                 int pageSize,
                                                                 String name) {
        pageNo--;
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // Page page = repository.findAll(pageable);

        Specification<Product> spec = null;

        if(name != null) {
             spec = Specification.where(ProductSpecifications.hasName(name));
        }
                //.and(ProductSpecifications.hasName(name));

        Page<Product> page = repository.findAll(spec, pageable);


        return CommonDomainModel.<List<Product>>builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .data(page.getContent())
                .build();
//        return repository.findAll(pageable);
    }


    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Product updateProduct(Product product) {
        Product existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }


}
