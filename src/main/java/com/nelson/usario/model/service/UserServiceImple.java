package com.nelson.usario.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nelson.usario.model.dao.RolDAO;
import com.nelson.usario.model.dao.UsuarioDAO;
import com.nelson.usario.model.entity.Rol;
import com.nelson.usario.model.entity.Usuario;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private RolDAO rolDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {

		Optional<Rol> optionalRoleUser = rolDao.findByName("ROLE_USER");
		List<Rol> roles = new ArrayList<>();
		optionalRoleUser.ifPresent(roles::add);

		if (user.isAdmin()) {
			Optional<Rol> optionalRoleAdmin = rolDao.findByName("ROLE_ADMIN");
			optionalRoleAdmin.ifPresent(roles::add);
		}

		user.setRoles(roles);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return usuarioDao.save(user);
	}

	@Override
	public boolean existsByUsername(String username) {

		return usuarioDao.existsByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return this.usuarioDao.findAll(pageable);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Usuario> findById(@NonNull Long id) {
		return usuarioDao.findById(id);
	}
	
	@Transactional
	@Override
	public Optional<Usuario> delete(Long id) {
		Optional<Usuario> userOptional = usuarioDao.findById(id);
		userOptional.ifPresent(userDb -> {
			usuarioDao.delete(userDb);
		});
		return userOptional;
	}

}
