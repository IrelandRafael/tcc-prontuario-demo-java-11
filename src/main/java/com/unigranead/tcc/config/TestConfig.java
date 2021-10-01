package com.unigranead.tcc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.repositories.LoginRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public void run(String... args) throws Exception {
		Login p1 = new Login(null, "rafael.estrela", "admin");
		Login p2 = new Login(null, "flavia.estrela", "admin");
		
		loginRepository.saveAll(Arrays.asList(p1,p2));
	}
}
