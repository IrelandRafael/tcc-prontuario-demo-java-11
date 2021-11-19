package com.unigranead.tcc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unigranead.tcc.entities.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{
	
	Login findByUsuario(String usuario);
	

}
