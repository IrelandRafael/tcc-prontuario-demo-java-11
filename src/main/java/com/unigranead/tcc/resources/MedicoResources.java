package com.unigranead.tcc.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigranead.tcc.entities.Medico;
import com.unigranead.tcc.services.MedicoServices;

@RestController 
@RequestMapping(value = "/medicos")
public class MedicoResources {

	@Autowired
	private MedicoServices service;
	
	@GetMapping
	public ResponseEntity<List<Medico>> findALL(){
		List<Medico> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{cfm}")
	public ResponseEntity<Medico> findById(@PathVariable String cfm){
		Medico obj = service.findById(cfm);
		return ResponseEntity.ok().body(obj);
	}
		
	
}
