package com.unigranead.tcc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private UserDetailsService userDetailsService;
 
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
          = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
        .antMatchers("/paciente/{idPaciente}").hasAnyAuthority("ADMIN", "ATENDENTE")
        .antMatchers("/pacientes/paciente/**").hasAuthority("PACIENTE")
        .antMatchers("/pacientes/**").hasAnyAuthority("ADMIN", "ATENDENTE")
        .antMatchers("/prontuarios/prontuario/**").hasAuthority("PACIENTE")
        .antMatchers("/prontuarios/**").hasAnyAuthority("ADMIN", "ATENDENTE")
        .antMatchers("/atendentes/**").hasAuthority("ADMIN")
        .antMatchers("/enfermagens/**").hasAuthority("ADMIN")
        .antMatchers("/funcionarios/**").hasAuthority("ADMIN")
        .antMatchers("/medicos/**").hasAuthority("ADMIN")
        .antMatchers("/logins/**").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
        .httpBasic();
    	
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
    
}