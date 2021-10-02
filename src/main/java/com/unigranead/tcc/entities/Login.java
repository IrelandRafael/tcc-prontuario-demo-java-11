package com.unigranead.tcc.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

 
@Entity
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idLogin;
	private String usuario;
	private String senha;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "login")
	private List<Paciente> pacientes = new ArrayList<>();
	
	public Login() {
		super();
	}

	public Login(Integer idLogin, String usuario, String senha) {
		super();
		this.idLogin = idLogin;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Integer getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
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
	

	public List<Paciente> getpacientes() {
		return pacientes;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idLogin, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(idLogin, other.idLogin) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Login [idLogin=" + idLogin + ", usuario=" + usuario + ", senha=" + senha + "]";
	}
	


}
