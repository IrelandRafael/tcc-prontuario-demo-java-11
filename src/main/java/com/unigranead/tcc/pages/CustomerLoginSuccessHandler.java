package com.unigranead.tcc.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.repositories.LoginRepository;
import com.unigranead.tcc.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomerLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Autowired
  private LoginRepository repo;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Override
  protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException{

    String targetUrl = determineTargetUrl(authentication);
    if (response.isCommitted()) {
      return;
    }
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    redirectStrategy.sendRedirect(request, response, targetUrl);
  }

  protected String determineTargetUrl(final Authentication authentication) {

    String url="/login?error=true";

    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    List<String> roles = new ArrayList<>();
    for (GrantedAuthority a : authorities) {
      roles.add(a.getAuthority());
    }

    if(roles.contains("ADMIN") || roles.contains("ATENDENTE") || roles.contains("ENFERMEIRA")) {
      url = "/list-pacientes";
    }
    else if(roles.contains("PACIENTE")) {
     Login login =  repo.findByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
     Paciente paciente = pacienteRepository.findByLoginIdLogin(login.getIdLogin());
     url = "/view-paciente/" + paciente.getIdPaciente();
    }
    return url;
  }

}
