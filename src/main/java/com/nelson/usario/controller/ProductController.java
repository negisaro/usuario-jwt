package com.nelson.usario.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelson.usario.model.entity.Product;
import com.nelson.usario.model.entity.Usuario;
import com.nelson.usario.model.service.ProductService;



import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping
	public List<Product> list() {
		return service.findAll();
	}

	@GetMapping("/page/{page}")
	public Page<Product> listPageable(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 5);
		return service.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> view(@PathVariable Long id) {
		Optional<Product> productOptional = service.findById(id);
		if (productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
		// valdation.validate(product, result);
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
		// valdation.validate(product, result);
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Product> productOptional = service.update(id, product);
		if (productOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Product> productOptional = service.delete(id);
		if (productOptional.isPresent()) {
			return ResponseEntity.ok(productOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	private ResponseEntity<?> validation(BindingResult result) {
		Map<String, String> errors = new HashMap<>();

		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}
}
