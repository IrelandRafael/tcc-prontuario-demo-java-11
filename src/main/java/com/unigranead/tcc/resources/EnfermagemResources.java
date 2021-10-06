package com.unigranead.tcc.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigranead.tcc.entities.Enfermagem;
import com.unigranead.tcc.services.EnfermagemServices;

@RestController 
@RequestMapping(value = "/enfermagens")
public class EnfermagemResources {

	@Autowired
	private EnfermagemServices service;
	
	@GetMapping
	public ResponseEntity<List<Enfermagem>> findALL(){
		List<Enfermagem> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{cfm}")
	public ResponseEntity<Enfermagem> findById(@PathVariable String cfm){
		Enfermagem obj = service.findById(cfm);
		return ResponseEntity.ok().body(obj);
	}
		
	
}
