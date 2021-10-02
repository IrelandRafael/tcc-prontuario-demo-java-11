package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Funcionario;
import com.unigranead.tcc.repositories.FuncionarioRepository;

@Service
public class FuncionarioServices {

	@Autowired
	private FuncionarioRepository repository;
	
	public List<Funcionario> findAll(){
		return repository.findAll();
	}
	
	public Funcionario findById(Integer idFuncionario) {
		Optional<Funcionario> obj = repository.findById(idFuncionario);
		return obj.get();
	}
	
}
