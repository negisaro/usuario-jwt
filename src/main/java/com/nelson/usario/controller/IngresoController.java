package com.nelson.usario.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.nelson.usario.model.entity.Ingresos;
import com.nelson.usario.model.service.IngresoService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

	@Autowired
	private IngresoService ingresoService;

	@GetMapping
	public List<Ingresos> listIngresos() {
		return ingresoService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Ingresos>  listIngresosById(@PathVariable Long id){
		return ingresoService.findById(id);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createVehiculo(@Valid @RequestBody Ingresos ingreso, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(ingresoService.save(ingreso));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Ingresos ingreso, BindingResult result, @PathVariable Long id) {
		
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Ingresos> ingresoOptional = ingresoService.update(id, ingreso);
		if (ingresoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(ingresoOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Ingresos> ingresoOptional = ingresoService.delete(id);
		if (ingresoOptional.isPresent()) {
			return ResponseEntity.ok(ingresoOptional.orElseThrow());
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
