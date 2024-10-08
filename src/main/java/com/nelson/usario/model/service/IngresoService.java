package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.nelson.usario.model.entity.Ingresos;

public interface IngresoService {
    
    List<Ingresos> findAll();

    Optional<Ingresos> findById(Long id);

    Page<Ingresos> findAll(Pageable pageable);

    Ingresos save(Ingresos ingresos);

    Optional<Ingresos> update(Long id, Ingresos ingresos);

    Optional<Ingresos> delete(Long id);

}
