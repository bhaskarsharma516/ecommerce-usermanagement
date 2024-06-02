package com.ecommerce.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ecommerce.usermanagement.repo.UserProfileRepo;

@Configuration
public class ApplicationConfig {

	 private final UserProfileRepo userProfileRepo;

	    public ApplicationConfig(UserProfileRepo userProfileRepo) {
	        this.userProfileRepo = userProfileRepo;
	    }

	    @Bean
	    UserDetailsService userDetailsService() {
	        return userPhoneNumber -> userProfileRepo.findByNumber(userPhoneNumber)
	        		.orElseThrow(() -> new UsernameNotFoundException("User not found"));              
	    }

	    @Bean
	    BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

	    @Bean
	    AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());

	        return authProvider;
	    }
}
