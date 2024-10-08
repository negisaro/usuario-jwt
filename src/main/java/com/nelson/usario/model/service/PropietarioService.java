package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nelson.usario.model.entity.Propietario;
import com.nelson.usario.model.entity.Usuario;


public interface PropietarioService {

    List<Propietario> findAll();

	Optional<Propietario> findById(Long id);
	
	Page<Propietario> findAll(Pageable pageable);

	Propietario save(Propietario propietario);

	Optional<Propietario> update(Long id, Propietario propietario);

	Optional<Propietario> delete(Long id);

	boolean findByDocumento(String documento);

}
