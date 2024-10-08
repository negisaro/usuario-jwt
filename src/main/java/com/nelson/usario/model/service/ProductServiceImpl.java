package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nelson.usario.model.dao.ProductRepository;
import com.nelson.usario.model.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	@Override
	public List<Product> findAll() {
		return (List<Product>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Product> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable) {
		return this.repository.findAll(pageable);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return repository.save(product);
	}

	@Override
	@Transactional
	public Optional<Product> update(Long id, Product product) {
		Optional<Product> productOptional = repository.findById(id);
		if (productOptional.isPresent()) {
			Product productDb = productOptional.orElseThrow();

			productDb.setSku(product.getSku());
			productDb.setName(product.getName());
			productDb.setDescription(product.getDescription());
			productDb.setPrice(product.getPrice());
			return Optional.of(repository.save(productDb));

		}
		return productOptional;
	}

	@Transactional
	@Override
	public Optional<Product> delete(Long id) {
		Optional<Product> productOptional = repository.findById(id);
		productOptional.ifPresent(productDb -> {
			repository.delete(productDb);
		});
		return productOptional;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsBySku(String sku) {
		return repository.existsBySku(sku);
	}

}
