package com.br.clinica.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.br.clinica.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long>{
	Cliente findById(long id);
	Cliente findByCpf(String cpf);
	Cliente findByEmail(String email);
	List<Cliente> findAll();
	Cliente save(Cliente cliente);
	void deleteById(Long id);
}
