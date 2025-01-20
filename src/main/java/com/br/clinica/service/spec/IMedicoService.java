package com.br.clinica.service.spec;

import java.util.List;
import com.br.clinica.domain.*;

public interface IMedicoService {
	Medico buscarPorId(Long id);
	List<Medico> buscarTodos();
	List<Medico> buscarPorEspecialidade(String especialidade);
	List<String> buscarEspecialidades(String especialidade);
	void salvar(Medico medico);
	void excluir(Long id);
	boolean medicoTemConsultas(Long id);
}
