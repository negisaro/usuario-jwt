package com.nelson.usario.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.nelson.usario.model.entity.Product;


public interface ProductRepository extends CrudRepository<Product, Long> {
    boolean existsBySku(String sku);
}
