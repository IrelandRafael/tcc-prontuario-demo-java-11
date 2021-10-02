package com.unigranead.tcc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unigranead.tcc.entities.Funcionario;
import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.entities.Medico;
import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.repositories.FuncionarioRepository;
import com.unigranead.tcc.repositories.LoginRepository;
import com.unigranead.tcc.repositories.MedicoRepository;
import com.unigranead.tcc.repositories.PacienteRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Override
	public void run(String... args) throws Exception {
		Login lgpc1 = new Login(null, "rafael.estrela", "pac");
		Login lgpc2 = new Login(null, "flavia.estrela", "pac");
		Login fn1 = new Login(null, "pedro.silva", "fun");
		Login fn2 = new Login(null, "maria.silva", "fun");
		
		
		
		Paciente p1 = new Paciente(null, "Rafael Estrela Silva", "(089) 9999-99-99", "111.111.111-11","Rua: 11, Casa: 11, Brasilia " , lgpc1);
		Paciente p2 = new Paciente(null, "Flavia Estrela Silva", "(089) 9999-22-22", "222.222.222-22","Rua: 22, Casa: 22, Brasilia " , lgpc2);
		
		Funcionario f1 = new Funcionario(null, "Pedro", fn1);
		Funcionario f2 = new Funcionario(null, "Maria", fn2);
		
		Medico m1 = new Medico("001", "cardiologista", f1);
		Medico m2 = new Medico("002", "ginecologista", f2);
		
		
		
		loginRepository.saveAll(Arrays.asList(lgpc1,lgpc2, fn1, fn2));
		pacienteRepository.saveAll(Arrays.asList(p1, p2));
		funcionarioRepository.saveAll(Arrays.asList(f1, f2));
		medicoRepository.saveAll(Arrays.asList(m1, m2));
	}
}
