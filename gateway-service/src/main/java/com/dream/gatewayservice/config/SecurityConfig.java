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

		http.authorizeExchange().pathMatchers("/main/", "/main", "/main/test","/*/*/*.css","/*/*/*.js","/*/*/*.jpg","/*/*/*.png").permitAll()
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

//=======================================================================================================================================================================================
//	//test error 페이지 핸들러 추가(3월 31일)
//	@Bean
//	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//
//		http
//			.authorizeExchange()
//								.pathMatchers("/menu/**", "/product/**").permitAll()
//								.pathMatchers("/manage/**").hasRole("ADMIN")
//								.pathMatchers("/loan/**", "/save/**").hasRole("USER")
//								.pathMatchers("/status/**", "/list/**").hasRole("MANAGER")
//			.anyExchange().authenticated()
//			.and().oauth2Login()
//			.and().logout()
//				.logoutUrl("/logout")
//				.logoutSuccessHandler(logoutSuccessHandler("http://192.168.1.54:8080/auth/realms/MSA/protocol/openid-connect/logout?redirect_uri=http://localhost:8000/menu/list"))
//			.and().exceptionHandling()
//				  .accessDeniedHandler(new CustomAccessDeniedHandler())
//			.and().csrf().disable();
//		return http.build();
//	}
//	
//	 public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
//	        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
//	        successHandler.setLogoutSuccessUrl(URI.create(uri));
//	        return successHandler;
//	    }
}
