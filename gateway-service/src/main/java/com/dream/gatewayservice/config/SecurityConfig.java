package com.dream.gatewayservice.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

		http.authorizeExchange().pathMatchers("/main/", "/actuator/**", "/main/test","/*/*/*.css","/*/*/*.js","/*/*/*.jpg","/*/*/*.png").permitAll()
		.and().authorizeExchange().anyExchange().authenticated().and().oauth2Login().and().logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(logoutSuccessHandler("http://192.168.1.54:8080/auth/realms/MSA/protocol/openid-connect/logout?redirect_uri=http://localhost:8000/main/"))
				.and()
				.csrf()
				.disable();
		return http.build();
	}
	 public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
	        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
	        successHandler.setLogoutSuccessUrl(URI.create(uri));
	        return successHandler;
	    }
}
