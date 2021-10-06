package com.unigranead.tcc.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Enfermagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cip;
	private String departamentoEnfermagem;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funionario;

	public Enfermagem() {
		super();
	}

	public Enfermagem(String cip, String departamentoEnfermagem, Funcionario funionario) {
		super();
		this.cip = cip;
		this.departamentoEnfermagem = departamentoEnfermagem;
		this.funionario = funionario;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getDepartamentoEnfermagem() {
		return departamentoEnfermagem;
	}

	public void setDepartamentoEnfermagem(String departamentoEnfermagem) {
		this.departamentoEnfermagem = departamentoEnfermagem;
	}

	public Funcionario getFunionario() {
		return funionario;
	}

	public void setFunionario(Funcionario funionario) {
		this.funionario = funionario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cip, departamentoEnfermagem, funionario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enfermagem other = (Enfermagem) obj;
		return Objects.equals(cip, other.cip) && Objects.equals(departamentoEnfermagem, other.departamentoEnfermagem)
				&& Objects.equals(funionario, other.funionario);
	}
	
	
	
}
