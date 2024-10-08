package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nelson.usario.model.entity.Vehiculo;

public interface VehiculoService {
	
	List<Vehiculo> findAll();
	Optional<Vehiculo> findById(Long id);
	Page<Vehiculo> findAll(Pageable pageable);
	Vehiculo save(Vehiculo vehiculo);
	Optional<Vehiculo> update(Long id, Vehiculo vehiculo);
	Optional<Vehiculo> delete(Long id);

}
