package com.br.clinica.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.br.clinica.domain.*;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Long>{
	Consulta findById(long id);
	List<Consulta> findAll();
	List<Consulta> findAllByCliente(Cliente cliente);
	List<Consulta> findAllByMedico(Medico Medico);
	Consulta findByClienteAndMedicoAndData(Cliente cliente, Medico medico, String data);
	Consulta save(Consulta consulta);
	Consulta deleteById(long id);
}
