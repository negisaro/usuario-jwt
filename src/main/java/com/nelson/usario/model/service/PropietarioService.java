package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import com.nelson.usario.model.entity.Propietario;


public interface PropietarioService {

    List<Propietario> findAll();

	Optional<Propietario> findById(Long id);

	Propietario save(Propietario propietario);

	Optional<Propietario> update(Long id, Propietario propietario);

	Optional<Propietario> delete(Long id);

	boolean findByDocumento(String documento);

}
