package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nelson.usario.model.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Optional<Product> findById(Long id);

	Page<Product> findAll(Pageable pageable);

	Product save(Product product);

	Optional<Product> update(Long id, Product product);

	Optional<Product> delete(Long id);

	boolean existsBySku(String sku);
}
