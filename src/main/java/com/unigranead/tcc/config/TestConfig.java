package com.unigranead.tcc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.repositories.LoginRepository;
import com.unigranead.tcc.repositories.PacienteRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public void run(String... args) throws Exception {
		Login lg1 = new Login(null, "rafael.estrela", "admin");
		Login lg2 = new Login(null, "flavia.estrela", "admin");
		
		
		Paciente p1 = new Paciente(null, "Rafael Estrela Silva", "(089) 9999-99-99", "111.111.111-11","Rua: 11, Casa: 11, Brasilia " , lg1);
		Paciente p2 = new Paciente(null, "Flavia Estrela Silva", "(089) 9999-22-22", "222.222.222-22","Rua: 22, Casa: 22, Brasilia " , lg2);
		
		loginRepository.saveAll(Arrays.asList(lg1,lg2));
		pacienteRepository.saveAll(Arrays.asList(p1, p2));
	}
}
