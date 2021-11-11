package com.unigranead.tcc.resources;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Login> insert(@RequestBody Login obj){
		obj = service.insert(obj); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idLogin}").buildAndExpand(obj.getIdLogin()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{idLogin}")
	public ResponseEntity<Void> delete(@PathVariable Integer idLogin){
		service.delete(idLogin);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{idLogin}")
	public ResponseEntity<Login> update(@PathVariable Integer idLogin, @RequestBody Login obj){
		obj = service.update(idLogin, obj);
		return ResponseEntity.ok().body(obj);
		
	}
	
}
