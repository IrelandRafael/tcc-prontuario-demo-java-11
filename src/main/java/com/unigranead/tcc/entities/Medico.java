package com.unigranead.tcc.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cfm;
	private String departamentoMedico;
	
	@ManyToOne
	@JoinColumn(name = "idFuncionario")
	private Funcionario funcionario; 

	public Medico() {
		super();
	}

	public Medico(String cfm, String departamentoMedico, Funcionario funcionario) {
		super();
		this.cfm = cfm;
		this.departamentoMedico = departamentoMedico;
		this.funcionario = funcionario;
	}

	public String getCfm() {
		return cfm;
	}
	
	public String getDepartamentoMedico() {
		return departamentoMedico;
	}

	public void setDepartamentoMedico(String departamentoMedico) {
		this.departamentoMedico = departamentoMedico;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public void setCfm(String cfm) {
		this.cfm = cfm;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cfm);
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(cfm, other.cfm);
	}

	

}
