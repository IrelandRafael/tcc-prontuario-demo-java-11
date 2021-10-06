package com.unigranead.tcc.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;


@Entity
public class Prontuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProntuario;
	@Column(columnDefinition="LONGTEXT")
	private String historico, medicamento, exame, diagnostico, condutaDoPaciente, condutaDiaria, alerta;
	
	@OneToOne
	@MapsId
	private Paciente paciente;


	public Prontuario() {
		super();
	}

	public Prontuario(Integer idProntuario, String historico, String medicamento, String exame, String diagnostico,
			String condutaDoPaciente, String condutaDiaria, String alerta, Paciente paciente) {
		super();
		this.idProntuario = idProntuario;
		this.historico = historico;
		this.medicamento = medicamento;
		this.exame = exame;
		this.diagnostico = diagnostico;
		this.condutaDoPaciente = condutaDoPaciente;
		this.condutaDiaria = condutaDiaria;
		this.alerta = alerta;
		this.paciente = paciente;
	}



	public Integer getIdProntuario() {
		return idProntuario;
	}

	public void setIdProntuario(Integer idProntuario) {
		this.idProntuario = idProntuario;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getCondutaDoPaciente() {
		return condutaDoPaciente;
	}

	public void setCondutaDoPaciente(String condutaDoPaciente) {
		this.condutaDoPaciente = condutaDoPaciente;
	}

	public String getCondutaDiaria() {
		return condutaDiaria;
	}

	public void setCondutaDiaria(String condutaDiaria) {
		this.condutaDiaria = condutaDiaria;
	}

	public String getAlerta() {
		return alerta;
	}

	public void setAlerta(String alerta) {
		this.alerta = alerta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProntuario);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prontuario other = (Prontuario) obj;
		return Objects.equals(idProntuario, other.idProntuario);
	}
	
	
	
}
