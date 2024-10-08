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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nelson.usario.model.entity.Propietario;
import com.nelson.usario.model.service.PropietarioService;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RestController
@RequestMapping("/api/propietarios")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @GetMapping
    public List<Propietario> listPropietario() {
        return propietarioService.findAll();
    }
    
    @GetMapping("/page/{page}")
	public Page<Propietario> listPageable(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return propietarioService.findAll(pageable);
	}

    @PostMapping("/create")
    public ResponseEntity<?> createPropietario(@Valid @RequestBody Propietario propietario, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(propietarioService.save(propietario));
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<?> deletePropietario(@PathVariable Long id) {
		Optional<Propietario> propietarioOptional = propietarioService.delete(id);
		if (propietarioOptional.isPresent()) {
			return ResponseEntity.ok(propietarioOptional.orElseThrow());
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
