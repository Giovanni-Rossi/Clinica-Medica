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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.br.clinica.domain.Medico;
import com.br.clinica.service.spec.IMedicoService;

@RestController
public class MedicoController {
	@Autowired
	private IMedicoService service;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		}catch(IOException e) {
			return false;
		}
	}
	
	private void parse(Medico medico, JSONObject json) {
		Object id = json.get("id");
		if(id != null) {
			if (id instanceof Integer) {
				medico.setId(((Integer) id).longValue());
			}
			else {
				medico.setId(((Long) id));
			}
		}
		medico.setEmail((String) json.get("email"));
		medico.setSenha((String) json.get("senha"));
		medico.setCRM((String) json.get("CRM"));
		medico.setEspecialidade((String) json.get("especialidade"));
		medico.setNome((String) json.get("nome"));
		medico.setPapel((String) json.get("papel"));
		
	}
	
	@GetMapping(path = "/medicos")
	public ResponseEntity <List<Medico>> lista(){
		List <Medico> lista = service.buscarTodos();
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/medicos/{id}")
	public ResponseEntity<Medico> lista(@PathVariable("id") long id){
		Medico medico = service.buscarPorId(id);
		if (medico == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(medico);
	}
	
	@GetMapping(path = "/medicos/especialidade/{especialidade}")
	public ResponseEntity<List<Medico>> lista(@PathVariable("especialidade") String especialidade){
		if(especialidade == null || especialidade.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		List<Medico> lista = service.buscarPorEspecialidade(especialidade);
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);	
	}
	
	@PostMapping(path = "/medicos")
	@ResponseBody
	public ResponseEntity<Medico> cria(@RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Medico medico = new Medico();
				parse(medico, json);
				service.salvar(medico);
				return ResponseEntity.ok(medico);
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@PutMapping(path = "/medicos/{id}")
	public ResponseEntity<Medico> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Medico medico = service.buscarPorId(id);
				if(medico == null) {
					return ResponseEntity.notFound().build();
				}else {
					parse(medico, json);
					service.salvar(medico);
					return ResponseEntity.ok(medico);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	@DeleteMapping(path = "/medicos/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id){
		Medico medico =  service.buscarPorId(id);
		if(medico == null)
			return ResponseEntity.notFound().build();
		else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}
