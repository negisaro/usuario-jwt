package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import com.nelson.usario.model.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Optional<Product> findById(Long id);

	Product save(Product product);

	Optional<Product> update(Long id, Product product);

	Optional<Product> delete(Long id);

	boolean existsBySku(String sku);
}
