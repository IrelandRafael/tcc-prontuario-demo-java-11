package com.unigranead.tcc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unigranead.tcc.entities.Atendente;
import com.unigranead.tcc.entities.Enfermagem;
import com.unigranead.tcc.entities.Funcionario;
import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.entities.Medico;
import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.entities.Prontuario;
import com.unigranead.tcc.repositories.AtendenteRepository;
import com.unigranead.tcc.repositories.EnfermagemRepository;
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
	
	@Autowired 
	private EnfermagemRepository enfermagemRepository;
	
	@Autowired
	private AtendenteRepository atendenteRepository;
	

	
	@Override
	public void run(String... args) throws Exception {
		Login lgpc1 = new Login(null, "rafael.estrela", "pac");
		Login lgpc2 = new Login(null, "fiona.silva", "pac");
		Login lfun3 = new Login(null, "pedro.silva", "fun");
		Login lfun4 = new Login(null, "maria.silva", "fun");
		Login lfun5 = new Login(null, "antonella.silva", "fun");
		Login lfun6 = new Login(null, "rafaela.silva", "fun");
		Login lfun7 = new Login(null, "joao.costa", "fun");
		Login lfun8 = new Login(null, "fabi.pereira", "fun");
		
		
		Funcionario f1 = new Funcionario(null, "Pedro", lfun3);
		Funcionario f2 = new Funcionario(null, "Maria", lfun4);
		Funcionario f3 = new Funcionario(null, "Antonella", lfun5);
		Funcionario f4 = new Funcionario(null, "Rafaela", lfun6);
		Funcionario f5 = new Funcionario(null, "Joao", lfun7);
		Funcionario f6 = new Funcionario(null, "Fabi", lfun8);
		
		
		Medico med1 = new Medico("001", "cardiologista", f1);
		Medico med2 = new Medico("002", "ginecologista", f2);
		
		Enfermagem enf1 = new Enfermagem("003", "Pediátrica", f3);
		Enfermagem enf2 = new Enfermagem("004", "geral", f4);	
		
		Atendente aten1 = new Atendente(null, f5);
		Atendente aten2 = new Atendente(null, f6);
			
		

		Paciente p1 = new Paciente(null, null,"Rafael Estrela Silva", "1101101 SSP/DF", "222.222.222-22", "Rua: 22, Casa: 22, Brasilia ","(061) 1111-11-11" ,lgpc1);
		Prontuario pront1 = new Prontuario(null, null, null, null, null, null, null, null, p1);
		
		loginRepository.saveAll(Arrays.asList(lgpc1, lgpc2, lfun3, lfun4, lfun5, lfun6, lfun7, lfun8));
		funcionarioRepository.saveAll(Arrays.asList(f1, f2, f3, f4, f5, f6));
		medicoRepository.saveAll(Arrays.asList(med1, med2));
		enfermagemRepository.saveAll(Arrays.asList(enf1, enf2));
		atendenteRepository.saveAll(Arrays.asList(aten1, aten2 ));
		pacienteRepository.saveAll(Arrays.asList(p1));
		
		p1.setProntuario(pront1);
		pacienteRepository.save(p1);
	

	}
}
