package com.nelson.usario.model.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import com.nelson.usario.model.entity.Usuario;

public interface UserService {

	// servicios para usuarios
	List<Usuario> findAll();

	Usuario save(Usuario user);

	boolean existsByUsername(String username);

	Page<Usuario> findAll(Pageable pageable);

	Optional<Usuario> findById(@NonNull Long id);

}
