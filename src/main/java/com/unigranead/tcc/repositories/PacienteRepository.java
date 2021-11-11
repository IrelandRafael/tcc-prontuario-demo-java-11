package com.unigranead.tcc.repositories;



import org.springframework.data.jpa.repository.JpaRepository;


import com.unigranead.tcc.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

	Paciente findByCpf(String cpf);

}
