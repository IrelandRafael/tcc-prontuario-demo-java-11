package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Atendente;
import com.unigranead.tcc.repositories.AtendenteRepository;

@Service
public class AtendenteServices {

	@Autowired
	private AtendenteRepository repository;
	
	public List<Atendente> findAll(){
		return repository.findAll();
	}
	
	public Atendente findById(Integer idAtendente) {
		Optional<Atendente> obj = repository.findById(idAtendente);
		return obj.get();
	}
	
}
