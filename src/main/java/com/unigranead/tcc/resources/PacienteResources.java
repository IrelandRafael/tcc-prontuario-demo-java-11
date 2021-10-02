package com.unigranead.tcc.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigranead.tcc.entities.Paciente;
import com.unigranead.tcc.services.PacienteServices;

@RestController 
@RequestMapping(value = "/pacientes")
public class PacienteResources {

	@Autowired
	private PacienteServices service;
	
	@GetMapping
	public ResponseEntity<List<Paciente>> findALL(){
		List<Paciente> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{idPaciente}")
	public ResponseEntity<Paciente> findById(@PathVariable Integer idPaciente){
		Paciente obj = service.findById(idPaciente);
		return ResponseEntity.ok().body(obj);
	}
		
	
}
