package com.br.clinica.service.spec;

import java.util.List;

import com.br.clinica.domain.*;

public interface IClienteService {
	Cliente buscarPorId(Long id);
	Cliente buscarPorcpf(String cpf);
	List<Cliente> buscarTodos();
	void salvar(Cliente cliente);
	void excluir(Long id);
}