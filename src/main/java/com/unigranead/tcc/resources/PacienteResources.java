package com.unigranead.tcc.resources;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Paciente> insert(@RequestBody Paciente obj){
		obj = service.insert(obj); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idPaciente}").buildAndExpand(obj.getIdPaciente()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{idPaciente}")
	public ResponseEntity<Void> delete(@PathVariable Integer idPaciente){
		service.delete(idPaciente);
		return ResponseEntity.noContent().build();
	}
		

}
