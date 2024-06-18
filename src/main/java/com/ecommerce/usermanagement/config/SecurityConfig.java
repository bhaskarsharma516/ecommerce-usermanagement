package com.ecommerce.usermanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.ecommerce.usermanagement.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	  	@Autowired
	    private JwtAuthenticationEntryPoint point;
	  
	    @Autowired
	    private JwtAuthenticationFilter filter;
	    
	    @Autowired
	    private LogoutHandler logoutHandler;
	
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 //auth.requestMatchers("/").authenticated()
		   http.csrf(csrf->csrf.disable());
		   http.cors(cors->cors.disable());
		   http.authorizeHttpRequests(auth->
				   auth.requestMatchers("/user/**").permitAll()
				   .requestMatchers("/auth/**","/").permitAll()
				 				   .anyRequest().authenticated());
		   
			http.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
	                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			http.logout(l->l.logoutUrl("/api/logout")
					.addLogoutHandler(logoutHandler)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.logoutSuccessHandler(
							(request,response,authentication)->SecurityContextHolder.clearContext()
							));
		
	        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		   
		  return  http.build();

	   }
	   
	   
	  }
