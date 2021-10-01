package com.unigranead.tcc.resources;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unigranead.tcc.entities.Login;

@RestController 
@RequestMapping(value = "/logins")
public class LoginResources {

	@GetMapping
	public ResponseEntity<Login> findALL(){
		Login p1 = new Login(null, "rafael.estrela", "admin");
		return ResponseEntity.ok().body(p1);
		
	}
}
