package com.unigranead.tcc.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFuncionario;
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "idLogin")
	private Login login;
	
	@JsonIgnore
	@OneToMany(mappedBy = "medico")
	private List<Medico> medicos = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "enfermagem")
	private List<Enfermagem> enfermagens = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "atendente")
	private List<Atendente> atendentes = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "funcionarios")
	private List<Paciente> pacientes = new ArrayList<>();
	
	public Funcionario() {
		super();
	}

	public Funcionario(Integer idFuncionario, String nome, Login login) {
		super();
		this.idFuncionario = idFuncionario;
		this.nome = nome;
		this.login = login;
	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	
	public List<Medico> getMedicos() {
		return medicos;
	}
	
	
	public List<Enfermagem> getEnfermagens() {
		return enfermagens;
	}


	public List<Atendente> getAtendentes() {
		return atendentes;
	}
	
	public List<Paciente> getPaccientes() {
		return pacientes;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idFuncionario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(idFuncionario, other.idFuncionario);
	}


}
