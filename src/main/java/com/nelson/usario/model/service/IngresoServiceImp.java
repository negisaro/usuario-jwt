package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nelson.usario.model.dao.IngresoRepository;
import com.nelson.usario.model.entity.Ingresos;

@Service
public class IngresoServiceImp implements IngresoService {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Ingresos> findAll() {
        return (List<Ingresos>) ingresoRepository.findAll();

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Ingresos> findById(Long id) {
        return ingresoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Ingresos> findAll(Pageable pageable) {
        return ingresoRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Ingresos save(Ingresos ingresos) {
        return ingresoRepository.save(ingresos);
    }

    @Transactional
    @Override
    public Optional<Ingresos> update(Long id, Ingresos ingresos) {
        Optional<Ingresos> ingresoUpdate = ingresoRepository.findById(id);
        ingresoUpdate.ifPresent(ingresoDb -> {
            ingresoRepository.save(ingresos);
        });
        return ingresoUpdate;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Ingresos> delete(Long id) {
        Optional<Ingresos> ingresosOptional = ingresoRepository.findById(id);
        ingresosOptional.ifPresent(ingresoDb -> {
            ingresoRepository.delete(ingresoDb);
        });
        return ingresosOptional;
    }

}
