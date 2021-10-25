package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.repositories.PacienteRepository;
import com.unigranead.tcc.services.exceptions.DataBaseException;
import com.unigranead.tcc.services.exceptions.ResourceNotFoundException;

@Service
public class PacienteServices {

	@Autowired
	private PacienteRepository repository;
	
	public List<Paciente> findAll(){
		return repository.findAll();
	}
	
	public Paciente findById(Integer idPaciente) {
		Optional<Paciente> obj = repository.findById(idPaciente);
		return obj.orElseThrow(() -> new ResourceNotFoundException(idPaciente));
	}
	
	public Paciente insert(Paciente obj) {
		return repository.save(obj);
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
		
		Paciente entity = repository.getById(idPaciente);
		updateData(entity, obj);
		return repository.save(entity);	
		
	}

	private void updateData(Paciente entity, Paciente obj) {
		
		entity.setNome(obj.getNome());
		entity.setRg(obj.getRg());
		entity.setCpf(obj.getCpf());
		entity.setTelefone(obj.getTelefone());
		
	}

}
