package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.repositories.LoginRepository;

@Service
public class LoginServices {

	@Autowired
	private LoginRepository repository;
	
	public List<Login> findAll(){
		return repository.findAll();
	}
	
	public Login findById(Integer idLogin) {
		Optional<Login> obj = repository.findById(idLogin);
		return obj.get();
	}
	
}
