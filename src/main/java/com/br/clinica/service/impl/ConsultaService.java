package com.br.clinica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.clinica.dao.IConsultaDAO;
import com.br.clinica.domain.Cliente;
import com.br.clinica.domain.Consulta;
import com.br.clinica.domain.Medico;
import com.br.clinica.service.spec.IConsultaService;

@Service
@Transactional(readOnly=false)
public class ConsultaService implements IConsultaService{
	@Autowired
	private IConsultaDAO dao;
	
	@Transactional(readOnly=true)
	public Consulta buscaPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly=true)
	public List<Consulta> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly=true)
	public List<Consulta> buscarTodos(Cliente cliente) {
		return dao.findAllByCliente(cliente);
	}

	@Transactional(readOnly=true)
	public List<Consulta> buscarTodos(Medico medico) {
		return dao.findAllByMedico(medico);
	}

	public void salvar(Consulta consulta) {
		dao.save(consulta);	
	}
	
	public void excluir(Long id) {
		dao.deleteById(id);;	
	}

	@Transactional(readOnly=true)
	public Consulta buscarPorClienteEMedicoEData(Cliente cliente, Medico medico, String data) {
		return dao.findByClienteAndMedicoAndData(cliente, medico, data);
	}

}
