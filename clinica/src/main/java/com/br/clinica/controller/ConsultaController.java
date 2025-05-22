package com.br.clinica.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.clinica.domain.Cliente;
import com.br.clinica.domain.Medico;
import com.br.clinica.domain.Consulta;
import com.br.clinica.service.spec.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ConsultaController {
	@Autowired
	private IConsultaService service;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IMedicoService medicoService;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		}catch(IOException e) {
			return false;
		}
	}
	
	private void parse(Consulta consulta, JSONObject json) {
		Object id = json.get("id");
		Object medico_id = json.get("medicoId");
		Object cliente_id = json.get("clienteId");
		
		if(id != null) {
			if(id instanceof Integer) {
				consulta.setId( ( (Integer) id).longValue() );
			}
			else {
				consulta.setId((Long) id);
			}
		}
		consulta.setData((String) json.get("data"));
		if(cliente_id != null) {
			Long clienteIdlong = (cliente_id instanceof Integer) ? ((Integer) cliente_id).longValue(): (Long) cliente_id;
			Cliente cliente = clienteService.buscarPorId(clienteIdlong);
			consulta.setCliente(cliente);
		}
		if(medico_id != null) {
			Long medicoIdlong = (medico_id instanceof Integer) ? ((Integer) medico_id).longValue(): (Long) medico_id;
			Medico medico = medicoService.buscarPorId(medicoIdlong);
			consulta.setMedico(medico);
		}
	}
	
	@GetMapping(path = "/consultas")
	public ResponseEntity <List<Consulta>> lista(){
		List<Consulta> lista = service.buscarTodos();
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/consultas/paciente/{id}")
	public ResponseEntity <List<Consulta>> listaPaciente(@PathVariable("id") Long id){
		Cliente cliente = clienteService.buscarPorId(id);
		if(cliente != null) {
			List<Consulta> lista = service.buscarTodos(cliente);
			return ResponseEntity.ok(lista);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@GetMapping(path = "/consultas/medico/{id}")
	public ResponseEntity <List<Consulta>> listaMedico(@PathVariable("id") Long id){
		Medico medico = medicoService.buscarPorId(id);
		if(medico != null) {
			List<Consulta> lista = service.buscarTodos(medico);
			return ResponseEntity.ok(lista);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping(path = "/consultas")
	public ResponseEntity<Consulta> cria(@RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Consulta consulta = new Consulta();
				parse(consulta, json);
				service.salvar(consulta);
				return ResponseEntity.ok(consulta);
			}
			else {
				return ResponseEntity.badRequest().build();
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
		
	}
	@PutMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> atualiza(@PathVariable("id") Long id, @RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Consulta consulta = service.buscaPorId(id);
				if(consulta == null) {
					return ResponseEntity.notFound().build();
				}else {
					parse(consulta, json);
					service.excluir(id);
					service.salvar(consulta);
					return ResponseEntity.ok(consulta);
				}
			}else {
				return ResponseEntity.badRequest().build();
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	
	}
	
	@DeleteMapping(path = "/consultas/{id}")
	public ResponseEntity<Consulta> delete(@PathVariable("id") Long id){
		Consulta consulta = service.buscaPorId(id);
		if(consulta == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
	
}
