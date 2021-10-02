package com.unigranead.tcc.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigranead.tcc.entities.Login;
import com.unigranead.tcc.services.LoginServices;

@RestController 
@RequestMapping(value = "/logins")
public class LoginResources {

	@Autowired
	private LoginServices service;
	
	@GetMapping
	public ResponseEntity<List<Login>> findALL(){
		List<Login> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{idLogin}")
	public ResponseEntity<Login> findById(@PathVariable Integer idLogin){
		Login obj = service.findById(idLogin);
		return ResponseEntity.ok().body(obj);
	}
		
	
}
