package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nelson.usario.model.dao.VehiculoRepository;
import com.nelson.usario.model.entity.Vehiculo;

@Service
public class VehiculoServiceImp implements VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Vehiculo> findAll() {
		return (List<Vehiculo>) vehiculoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Vehiculo> findById(Long id) {
		return vehiculoRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Vehiculo> findAll(Pageable pageable) {
		return vehiculoRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Vehiculo save(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	@Transactional
	public Optional<Vehiculo> update(Long id, Vehiculo vehiculo) {
		Optional<Vehiculo> vehiculoUpdate = vehiculoRepository.findById(id);
		vehiculoUpdate.ifPresent(vehiculoDb -> {
			vehiculoRepository.save(vehiculo);
		});
		return vehiculoUpdate;
	}

	@Override
	@Transactional
	public Optional<Vehiculo> delete(Long id) {
		Optional<Vehiculo> vehiculoDelete = vehiculoRepository.findById(id);
		vehiculoDelete.ifPresent(vehiculoDb -> {
			vehiculoRepository.delete(vehiculoDb);
		});
		return vehiculoDelete;
	}
}
