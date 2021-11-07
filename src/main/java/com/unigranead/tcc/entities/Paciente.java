package com.unigranead.tcc.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaciente;
	private String Foto;
	private String nome, rg, cpf, endereco, telefone;
	
	@Transient
	private String usuario, senha;
	
	@ManyToOne
	@JoinColumn(name = "idLogin")
	private Login login;

	@ManyToMany
    @JoinTable(
        name = "paciente_prontuario",
        joinColumns = @JoinColumn(name = "idPaciente"),
        inverseJoinColumns = @JoinColumn(name = "idFuncionario")
    )
	private List<Funcionario> funcionarios = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
	private Prontuario prontuario;
	
	public Paciente() {
		super();
	}

		
	public Paciente(Integer idPaciente, String foto, String nome, String rg, String cpf, String endereco,
			String telefone, Login login, Prontuario prontuario) {
		super();
		this.idPaciente = idPaciente;
		Foto = foto;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.login = login;
		this.prontuario = prontuario;
	}


	public Integer getIdPaciente() {
		return idPaciente;
	}


	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}


	public String getFoto() {
		return Foto;
	}


	public void setFoto(String foto) {
		Foto = foto;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public void setProntuario(Prontuario prontuario) {
		this.prontuario = prontuario;
	}


	public Prontuario getProntuario() {
		return prontuario;
	}


	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	

	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	
	
	
	
}
