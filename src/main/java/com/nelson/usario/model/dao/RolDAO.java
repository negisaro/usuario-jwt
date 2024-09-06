package com.nelson.usario.model.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.nelson.usario.model.entity.Rol;


@Repository
public interface RolDAO extends CrudRepository<Rol, Long> {
	Optional<Rol> findByName(String name);
}
