package com.nelson.usario.configuration.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.nelson.usario.security.filter.JwtAuthenticationFilter;
import com.nelson.usario.security.filter.JwtValidationFilter;

import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain fiolterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authz -> authz
						.requestMatchers(HttpMethod.GET, 	"/api/users", "/api/users/page/{page}", "/api/products/page/{page}",
														 	"/api/propietarios/page/{page}","/api/products",
															"/api/propietarios", "/api/vehiculos", "/api/ingresos").permitAll()
						.requestMatchers(HttpMethod.POST, 	"/api/users/register", "/api/products/create", "/api/vehiculos/**",
															"/api/propietarios/create", "/api/vehiculos/**").permitAll()
						.requestMatchers(HttpMethod.DELETE, "/api/users/{id}", "/api/products/{id}",
															"/api/propietarios/{id}", "/api/vehiculos/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, 	"/api/users/{id}", "/api/products/{id}").hasAnyRole("USER", "ADMIN")
						.requestMatchers(HttpMethod.PUT, 	"/api/users/{id}", "/api/products/{id}").hasRole("ADMIN")
						.anyRequest().authenticated())
				.cors(cors -> cors.configurationSource(configurationSource()))
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtValidationFilter(authenticationManager())).csrf(config -> config.disable())
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOriginPatterns(Arrays.asList("*"));
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
		config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean FilterRegistrationBean<CorsFilter> corsFilter() {
	 FilterRegistrationBean<CorsFilter> corsBean = new
	 FilterRegistrationBean<CorsFilter>( new
	 CorsFilter(this.configurationSource()));
	 corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE); return corsBean; }
	 

}
