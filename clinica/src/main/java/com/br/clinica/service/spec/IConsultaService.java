package com.br.clinica.service.spec;

import java.util.List;

import com.br.clinica.domain.*;

public interface IConsultaService {
	Consulta buscaPorId(Long id);
	List<Consulta> buscarTodos();
	List<Consulta> buscarTodos(Cliente cliente);
	List<Consulta> buscarTodos(Medico medico);
	void salvar(Consulta consulta);
	void excluir(Long id);
	Consulta buscarPorClienteEMedicoEData(Cliente cliente, Medico medico, String data);
}
