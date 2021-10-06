package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Enfermagem;
import com.unigranead.tcc.repositories.EnfermagemRepository;

@Service
public class EnfermagemServices {

	@Autowired
	private EnfermagemRepository repository;
	
	public List<Enfermagem> findAll(){
		return repository.findAll();
	}
	
	public Enfermagem findById(String cip) {
		Optional<Enfermagem> obj = repository.findById(cip);
		return obj.get();
	}
	
}
