package com.nelson.usario.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.nelson.usario.model.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends PagingAndSortingRepository<Vehiculo, Long>, CrudRepository<Vehiculo, Long> {
	@SuppressWarnings("null")
	Page<Vehiculo> findAll(Pageable pageable);
}
