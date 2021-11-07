package com.unigranead.tcc.services.exceptions;

import com.unigranead.tcc.entities.Paciente;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException (Object idPaciente) {
		super("Resource not found. id Paciente "+idPaciente);
	}
	

}
