package com.nelson.usario.model.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.nelson.usario.model.entity.Usuario;

@Repository
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long>, CrudRepository<Usuario, Long> {

	boolean existsByUsername(String username);

	Optional<Usuario> findByUsername(String username);

	Page<Usuario> findAll(Pageable pageable);

}
