package com.nelson.usario.controller;

import java.util.HashMap;
import java.util.List;
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
import com.nelson.usario.model.entity.Vehiculo;
import com.nelson.usario.model.service.VehiculoService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoService;

	@GetMapping
	public List<Vehiculo> listVehiculos() {
		return vehiculoService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id) {
		Optional<Vehiculo> vehiculo = vehiculoService.findById(id);
		return vehiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PostMapping("/create")
	public ResponseEntity<?> createVehiculo(@Valid @RequestBody Vehiculo vehiculo, BindingResult result) {
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Vehiculo crearVehiculo = vehiculoService.save(vehiculo);
		return ResponseEntity.status(HttpStatus.CREATED).body(crearVehiculo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateVehiculo(@Valid @RequestBody Vehiculo vehiculo, BindingResult result, @PathVariable Long id) {
		
		if (result.hasFieldErrors()) {
			return validation(result);
		}
		Optional<Vehiculo> vehiculoOptional = vehiculoService.update(id, vehiculo);
		if (vehiculoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoOptional.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVehiculo(@PathVariable Long id) {
		Optional<Vehiculo> vehiculoDelete = vehiculoService.delete(id);
		if (vehiculoDelete.isPresent()) {
			return ResponseEntity.ok(vehiculoDelete.orElseThrow());
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
