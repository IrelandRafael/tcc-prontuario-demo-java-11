package com.unigranead.tcc.config;

import com.unigranead.tcc.pages.CustomerLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomerLoginSuccessHandler sucessHandler;

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
      String loginPage = "/login";
      String logoutPage = "/logout";

    	http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(loginPage).permitAll()
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

        .antMatchers( "/configuration/**", "/webjars/**").permitAll()
        .antMatchers("/list-pacientes").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/view-paciente").hasAnyAuthority("PACIENTE", "ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/view-historico").hasAnyAuthority("PACIENTE")
        .antMatchers("/view-medicamento").hasAnyAuthority("PACIENTE")
        .antMatchers("/view-exame").hasAnyAuthority("PACIENTE")
        .antMatchers("/view-diagnostico").hasAnyAuthority("PACIENTE")
        .antMatchers("/edit-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/atualizar-paciente").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/delete-paciente").hasAuthority("ADMIN")
        .antMatchers("/edit-historico-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/atualizar-prontuario").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")

        .antMatchers("/edit-medicamento-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/edit-exame-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/edit-diag-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/atualizar-exame").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")

        .antMatchers("/edit-conduta-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/edit-conduta-diaria-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/edit-alerta-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")

        .antMatchers("/view-conduta-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/view-conduta-diaria-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/view-alerta-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")

        .antMatchers("/criar-paciente-form").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")
        .antMatchers("/criar-paciente").hasAnyAuthority("ADMIN", "ATENDENTE", "ENFERMEIRA")

        .anyRequest().authenticated()
        .and()
        .authorizeRequests()
          .and().formLogin()
          .loginPage(loginPage)
          .loginPage("/")
          .successHandler(sucessHandler)
          .failureUrl("/login?error=true")
          .usernameParameter("user_name")
          .passwordParameter("password");
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/vendor/**");
    }


}
