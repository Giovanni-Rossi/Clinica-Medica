package com.br.clinica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.clinica.dao.IMedicoDAO;
import com.br.clinica.domain.Medico;
import com.br.clinica.service.spec.IMedicoService;

@Service
@Transactional(readOnly=false)

public class MedicoService implements IMedicoService{
	@Autowired
	private IMedicoDAO dao;
	
	@Transactional(readOnly = true)
	public Medico buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Medico> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly=true)
	public List<Medico> buscarPorEspecialidade(String especialidade) {
		return dao.findByEspecialidade(especialidade);
	}

	@Transactional(readOnly = true)
	public List<String> buscarEspecialidades(String especialidade) {
		return dao.findAllByEspecialidade(especialidade);
	}

	@Override
	public void salvar(Medico medico) {
		dao.save(medico);
		
	}

	@Override
	public void excluir(Long id) {
		dao.deleteById(id);
		
	}

	@Override
	public boolean medicoTemConsultas(Long id) {
		return !dao.findById(id.longValue()).getConsultas().isEmpty();
	}
}
