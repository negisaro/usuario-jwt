package com.nelson.usario.auht.handler;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		// obtener el mapa de mensajes SessionFlashMapManager
		SessionFlashMapManager flahsMapManager = new SessionFlashMapManager();
		// obtener el FlashMap: contiene el listado(Map) de mensajes flash
		FlashMap flashMap = new FlashMap();
		flashMap.put("success", authentication.getName() + " se ha auteticado de forma correcta !");
		
		if (authentication != null) {
			logger.info("*** " + authentication.getName() + " se ha auteticado de forma correcta !");
		}
		
		flahsMapManager.saveOutputFlashMap(flashMap, request, response);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
