package com.unigranead.tcc.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Atendente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAtendente;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")	
	private Funcionario atendente;
	
	

	public Atendente() {
		super();
	}

	public Atendente(Integer idAtendente, Funcionario funcionario) {
		super();
		this.idAtendente = idAtendente;
		this.atendente = funcionario;
	}

	public Integer getIdAtendente() {
		return idAtendente;
	}

	public void setIdAtendente(Integer idAtendente) {
		this.idAtendente = idAtendente;
	}

	public Funcionario getFuncionario() {
		return atendente;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.atendente = funcionario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atendente, idAtendente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atendente other = (Atendente) obj;
		return Objects.equals(atendente, other.atendente) && Objects.equals(idAtendente, other.idAtendente);
	}

}
