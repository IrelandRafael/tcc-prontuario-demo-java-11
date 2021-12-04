package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

		if(obj.getHistorico() != null && !obj.getHistorico().equals(entity.getHistorico()))
			entity.setHistorico(obj.getHistorico());

		if(obj.getMedicamento() != null && !obj.getMedicamento().equals(entity.getMedicamento()))
			entity.setMedicamento(obj.getMedicamento());

		if(obj.getExame() != null && !obj.getExame().equals(entity.getExame()))
			entity.setExame(obj.getExame());

		if(obj.getDiagnostico() != null && !obj.getDiagnostico().equals(entity.getDiagnostico()))
			entity.setDiagnostico(obj.getDiagnostico());

		if(obj.getCondutaDoPaciente() != null && !obj.getCondutaDoPaciente().equals(entity.getCondutaDoPaciente()))
			entity.setCondutaDoPaciente(obj.getCondutaDoPaciente());

		if(obj.getCondutaDiaria() != null && !obj.getCondutaDiaria().equals(entity.getCondutaDiaria()))
			entity.setCondutaDiaria(obj.getCondutaDiaria());

		if(obj.getAlerta() != null && !obj.getAlerta().equals(entity.getAlerta()))
			entity.setAlerta(obj.getAlerta());
	}
	
	
}
