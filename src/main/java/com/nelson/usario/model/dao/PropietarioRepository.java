package com.nelson.usario.model.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.nelson.usario.model.entity.Propietario;

@Repository
public interface PropietarioRepository extends CrudRepository<Propietario, Long>, PagingAndSortingRepository<Propietario, Long>  {

    boolean findByDocumento(String documento);

    @SuppressWarnings("null")
	Page<Propietario> findAll(Pageable pageable);
}
