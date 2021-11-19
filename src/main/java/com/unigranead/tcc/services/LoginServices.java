package com.unigranead.tcc.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.repositories.LoginRepository;
import com.unigranead.tcc.services.exceptions.DataBaseException;
import com.unigranead.tcc.services.exceptions.ResourceNotFoundException;

@Service
public class LoginServices {

	@Autowired
	private LoginRepository repository;
	
	public List<Login> findAll(){
		return repository.findAll();
	}
	
	public Login findByUser(String usuario) {
		return  repository.findByUsuario(usuario);
		
	}
	
	
	public Login findById(Integer idLogin) {
		Optional<Login> obj = repository.findById(idLogin);
		return obj.get();
	}
	
	public Login insert(Login obj) {
		return repository.save(obj);
	}
	
	public void delete(Integer idLogin) {
		try {
			repository.deleteById(idLogin);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idLogin);
		} catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	
	public Login update(Integer idLogin, Login obj) {
		try {
			Login entity = repository.getById(idLogin);
			updateData(entity, obj);
			return repository.save(entity);	
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(idLogin);
		}
	}

	private void updateData(Login entity, Login obj) {
		
		entity.setUsuario(obj.getUsuario());
		entity.setSenha(obj.getSenha());
		
	}
	
}
