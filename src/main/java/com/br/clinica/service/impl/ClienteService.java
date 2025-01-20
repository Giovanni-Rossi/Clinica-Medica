package com.br.clinica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.clinica.dao.IClienteDAO;
import com.br.clinica.domain.Cliente;
import com.br.clinica.service.spec.IClienteService;

@Service
@Transactional(readOnly=false)

public class ClienteService implements IClienteService {
	@Autowired
	private IClienteDAO dao;

	@Transactional(readOnly=true)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly=true)
	public Cliente buscarPorcpf(String cpf) {
		return dao.findByCpf(cpf);
	}

	@Transactional(readOnly=true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

	public void salvar(Cliente cliente) {
		dao.save(cliente);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

}
/*package com.br.clinica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.clinica.dao.IClienteDAO;
import com.br.clinica.domain.Cliente;
import com.br.clinica.service.spec.IClienteService;

@Service
@Transactional(readOnly=false)

public class ClienteService implements IClienteService{
	@Autowired
	private IClienteDAO dao;
	
	@Transactional(readOnly= true)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly=true)
	public Cliente buscarPorCPF(String cpf) {
		return dao.findByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

	public void salvar(Cliente cliente) {
		dao.save(cliente);
		
	}

	public void excluir(Long id) {
		dao.deleteById(id);
		
	}

}*/
