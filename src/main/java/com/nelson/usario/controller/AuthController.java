package com.nelson.usario.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nelson.usario.model.entity.Usuario;
import com.nelson.usario.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

//controllers/AuthController.java
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService service;

	@PostMapping("/signup")
	public Authentication attemptAuthentication(@RequestBody @Valid Usuario user, HttpServletRequest request)
			throws AuthenticationException {

		user = null;
		String username = null;
		String password = null;

		try {
			user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			username = user.getUsername();
			password = user.getPassword();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		return authenticationManager.authenticate(authenticationToken);
	}

	@PostMapping("/signin")
	public String[] signIn(@RequestBody @Valid Usuario user) {

		String username = null;
		String password = null;

		username = user.getUsername();
		password = user.getPassword();

		String[] usuario = new String[] { username, password };

		return usuario;

	}
}
