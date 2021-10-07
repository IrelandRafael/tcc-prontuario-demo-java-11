package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.repositories.PacienteRepository;

@Service
public class PacienteServices {

	@Autowired
	private PacienteRepository repository;
	
	public List<Paciente> findAll(){
		return repository.findAll();
	}
	
	public Paciente findById(Integer idPaciente) {
		Optional<Paciente> obj = repository.findById(idPaciente);
		return obj.get();
	}
	
	public Paciente insert(Paciente obj) {
		return repository.save(obj);
	}
	
	public void delete(Integer idPaciente) {
		repository.deleteById(idPaciente);
	}
	
	
	public Paciente update(Integer idPaciente, Paciente obj) {
		Paciente entity = repository.getById(idPaciente);
		updateData(entity, obj);
			return repository.save(entity);
		
	}

	private void updateData(Paciente entity, Paciente obj) {
		obj.setFoto(obj.getFoto());
		obj.setNome(obj.getNome());
		obj.setRg(obj.getRg());
		obj.setCpf(obj.getCpf());
		obj.setEndereco(obj.getEndereco());
	}
}
