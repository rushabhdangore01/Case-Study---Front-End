package com.cms.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cms.jwt.EntryPointJwt;
import com.cms.jwt.TokenFilter;
import com.cms.service.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletResponse;



@Configuration
@EnableGlobalMethodSecurity( 
	prePostEnabled = true)
public class SecurityConfig { 

  	@Autowired
	TokenFilter filter;

	@Autowired
	UserDetailsServiceImpl uds;

	@Autowired
	EntryPointJwt entryPoint;
	
	@Bean
    public SecurityFilterChain doFilter(HttpSecurity http) throws Exception {
         http.authorizeHttpRequests().requestMatchers("/app/**").permitAll().requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN").anyRequest().authenticated().and()
        .formLogin().and().csrf().disable().cors().disable().userDetailsService(uds).exceptionHandling()
        .authenticationEntryPoint((request, response, authException) -> response
                .sendError(HttpServletResponse
                .SC_UNAUTHORIZED, authException.getMessage()))
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    
  }

  @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
