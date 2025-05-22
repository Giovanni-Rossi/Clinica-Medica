package com.br.clinica.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.br.clinica.domain.Medico;

@SuppressWarnings("unchecked")
public interface IMedicoDAO extends CrudRepository <Medico, Long>{
	Medico findById(long id);
	Medico findByEmail(String email);
	Medico findByCRM(String CRM);
	List<Medico> findByEspecialidade(String Especialidade);
	List<String> findAllByEspecialidade(String especialidade);
	List<Medico> findAll();
	Medico save(Medico Medico);
	void deleteById(Long id);
}
