package com.unigranead.tcc.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigranead.tcc.entities.Atendente;
import com.unigranead.tcc.services.AtendenteServices;

@RestController 
@RequestMapping(value = "/atendentes")
public class AtendenteResources {

	@Autowired
	private AtendenteServices service;
	
	@GetMapping
	public ResponseEntity<List<Atendente>> findALL(){
		List<Atendente> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{idAtendente}")
	public ResponseEntity<Atendente> findById(@PathVariable Integer idAtendente){
		Atendente obj = service.findById(idAtendente);
		return ResponseEntity.ok().body(obj);
	}
		
	
}
