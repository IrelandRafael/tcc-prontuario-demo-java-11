package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Medico;
import com.unigranead.tcc.repositories.MedicoRepository;

@Service
public class MedicoServices {

	@Autowired
	private MedicoRepository repository;
	
	public List<Medico> findAll(){
		return repository.findAll();
	}
	
	public Medico findById(String cfm) {
		Optional<Medico> obj = repository.findById(cfm);
		return obj.get();
	}
	
}
