package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nelson.usario.model.dao.PropietarioRepository;
import com.nelson.usario.model.entity.Propietario;

@Service
public class PropietarioServiceImp implements PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Propietario> findAll() {
        return (List<Propietario>) propietarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Propietario> findById(Long id) {
        return propietarioRepository.findById(id);
    }

    @Override
    public Propietario save(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }

    @Override
    public Optional<Propietario> update(Long id, Propietario propietario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Optional<Propietario> delete(Long id) {
        Optional<Propietario> propietarioOptional = propietarioRepository.findById(id);
        propietarioOptional.ifPresent(propietarioDb -> {
            propietarioRepository.delete(propietarioDb);
        });
        return propietarioOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findByDocumento(String documento) {
        return propietarioRepository.findByDocumento(documento);
    }
}
