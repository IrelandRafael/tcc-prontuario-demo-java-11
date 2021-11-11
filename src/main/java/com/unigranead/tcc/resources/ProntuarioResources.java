package com.unigranead.tcc.resources;


import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unigranead.tcc.entities.Prontuario;
import com.unigranead.tcc.services.ProntuarioServices;
import com.unigranead.tcc.services.exceptions.DataBaseException;

@RestController 
@RequestMapping(value = "/prontuarios")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProntuarioResources {

	@Autowired
	private ProntuarioServices service;
	
	@GetMapping
	public ResponseEntity<List<Prontuario>> findALL(){
		List<Prontuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value = "/prontuario/{idPaciente}")
	public ResponseEntity<Prontuario> findProntuarioByPacienteId(@PathVariable Integer idPaciente){
		Prontuario obj = service.findById(idPaciente);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{idProntuario}")
	public ResponseEntity<Prontuario> findById(@PathVariable Integer idProntuario){
		Prontuario obj = service.findById(idProntuario);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Prontuario> insert(@RequestBody Prontuario obj){
		obj = service.insert(obj); 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idprontuario}").buildAndExpand(obj.getIdProntuario()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{idProntuario}")
	public ResponseEntity<Void> delete(@PathVariable Integer idProntuario){
		service.delete(idProntuario);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{idProntuario}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Prontuario> update(@PathVariable Integer idProntuario, @RequestPart("prontuario") String prontuario, 
			@RequestPart("exame") MultipartFile exame){
		Prontuario prontuarioObj = converteStringToProntuario(prontuario, exame);
		prontuarioObj = service.update(idProntuario, prontuarioObj);
		return ResponseEntity.ok().body(prontuarioObj);
	}
	
	private Prontuario converteStringToProntuario(String prontuario, MultipartFile exame) {
		ObjectMapper objectMapper = new ObjectMapper();
		Prontuario prontuarioObjeto = null;
		try {
			prontuarioObjeto = objectMapper.readValue(prontuario, Prontuario.class);
			prontuarioObjeto.setExame(exame.getBytes());
		} catch (JsonProcessingException e) {
			throw new DataBaseException("Erro na conversao de dados do prontuario");
		} catch (IOException e) {
			throw new DataBaseException("Erro na conversao de dados do prontuario");
		}
		
		return prontuarioObjeto;
	}

}
