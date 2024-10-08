package com.nelson.usario.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.nelson.usario.model.entity.Ingresos;

@Repository
public interface IngresoRepository  extends PagingAndSortingRepository<Ingresos, Long>, CrudRepository<Ingresos, Long> {

}
