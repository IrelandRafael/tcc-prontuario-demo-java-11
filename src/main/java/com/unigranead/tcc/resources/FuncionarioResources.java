package com.unigranead.tcc.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigranead.tcc.entities.Funcionario;
import com.unigranead.tcc.services.FuncionarioServices;

@RestController 
@RequestMapping(value = "/funcionarios")
public class FuncionarioResources {

	@Autowired
	private FuncionarioServices service;
	
	@GetMapping
	public ResponseEntity<List<Funcionario>> findALL(){
		List<Funcionario> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/{idFuncionario}")
	public ResponseEntity<Funcionario> findById(@PathVariable Integer idFuncionario){
		Funcionario obj = service.findById(idFuncionario);
		return ResponseEntity.ok().body(obj);
	}
		
	
}
