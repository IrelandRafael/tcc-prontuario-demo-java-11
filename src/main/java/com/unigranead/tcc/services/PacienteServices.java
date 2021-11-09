package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties.Session;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysql.cj.xdevapi.SessionFactory;
import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.entities.Prontuario;
import com.unigranead.tcc.repositories.PacienteRepository;
import com.unigranead.tcc.services.exceptions.DataBaseException;
import com.unigranead.tcc.services.exceptions.ResourceNotFoundException;

@Service
public class PacienteServices {

	@Autowired
	private PacienteRepository repository;
	
	@Autowired
	private LoginServices loginServices;
	
	@Autowired
	private ProntuarioServices prontuarioServices;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public List<Paciente> findAll(){
		return repository.findAll();
	}
	
	public Paciente findById(Integer idPaciente) {
		Optional<Paciente> obj = repository.findById(idPaciente);
		return obj.orElseThrow(() -> new ResourceNotFoundException(idPaciente));
	}
	
	public Paciente findByCpf(String cpf) {
		try {
			Paciente obj = repository.findByCpf(cpf);
			return obj;
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(cpf);
		}
	}
	
	
	public Paciente insert(Paciente obj) {

		 Paciente paciente2 = repository.findByCpf(obj.getCpf());
		 Paciente paciente;
		 
		 if (paciente2.getCpf() != null) {
			 throw new DataBaseException("Erro ao criar paciente.  CPF existente!");
		 }else {
			 
			paciente = repository.save(obj);
			if(paciente != null && paciente.getIdPaciente() != null) {
				 Login login = new Login();
				 login.setUsuario(obj.getUsuario());
				 login.setSenha(encoder.encode(obj.getSenha()));
				 login.setPermissao("PACIENTE");
				 loginServices.insert(login);

				 Prontuario prontuario = new Prontuario();
				 prontuario.setPaciente(paciente);
				 paciente.setProntuario(prontuario);
				 prontuarioServices.insert(prontuario);
				 
				 paciente.setLogin(login);
				 repository.save(paciente);
			 } else  {
				 throw new DataBaseException("Erro ao criar paciente. Tente novamente!");
			 }
		 }	
		
				 
		 
		 return paciente;
	}
	
	public void delete(Integer idPaciente) {
		try {
			repository.deleteById(idPaciente);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idPaciente);
		} catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	
	public Paciente update(Integer idPaciente, Paciente obj) {
		try {
			Paciente entity = repository.getById(idPaciente);
			updateData(entity, obj);
			return repository.save(entity);	
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idPaciente);
		}
	}

	private void updateData(Paciente entity, Paciente obj) {
		
		entity.setNome(obj.getNome());
		entity.setRg(obj.getRg());
		entity.setCpf(obj.getCpf());
		entity.setTelefone(obj.getTelefone());
		
	}


}
