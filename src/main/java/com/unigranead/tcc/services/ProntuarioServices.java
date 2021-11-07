package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.entities.Prontuario;
import com.unigranead.tcc.repositories.ProntuarioRepository;
import com.unigranead.tcc.services.exceptions.DataBaseException;
import com.unigranead.tcc.services.exceptions.ResourceNotFoundException;

@Service
public class ProntuarioServices {

	@Autowired
	private ProntuarioRepository repository;
	
	public List<Prontuario> findAll(){
		return repository.findAll();
	}
	
	public Prontuario findById(Integer idProntuario) {
		Optional<Prontuario> obj = repository.findById(idProntuario);
		return obj.orElseThrow(() -> new ResourceNotFoundException(idProntuario));
	}
	
	public Prontuario insert(Prontuario obj) {
		return repository.save(obj);
	}
	
	public void delete(Integer idProntuario) {
		try {
			repository.deleteById(idProntuario);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idProntuario);
		} catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	
	public Prontuario update(Integer idProntuario, Prontuario obj) {
		try {
			Prontuario entity = repository.getById(idProntuario);
			updateData(entity, obj);
			return repository.save(entity);	
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idProntuario);
		}
	}

	private void updateData(Prontuario entity, Prontuario obj) {
		
		entity.setHistorico(obj.getHistorico());
		entity.setMedicamento(obj.getMedicamento());
		entity.setExame(obj.getExame());
		entity.setDiagnostico(obj.getDiagnostico());
		entity.setCondutaDoPaciente(obj.getCondutaDoPaciente());
		entity.setCondutaDiaria(obj.getCondutaDiaria());
		entity.setAlerta(obj.getAlerta());
	}
	
}
