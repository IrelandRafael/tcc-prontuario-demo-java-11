package com.unigranead.tcc.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.unigranead.tcc.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Paciente findByCpf(String cpf);

	Paciente findByLoginIdLogin(Integer idLogin);

	List<Paciente> findByNomeLike(String nome);

}
