package com.br.clinica.domain;

import jakarta.persistence.Entity;
import com.br.clinica.domain.Consulta;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="Medico")
public class Medico extends AbstractEntity<Long>{
	@NotBlank
	@Column(nullable = false, length = 30)
	private String email;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String senha;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String CRM;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String nome;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String especialidade;
	
	@NotBlank
	@Column(nullable = false, length = 10)
	private String papel;
	
	@Column(nullable=false)
    private boolean enabled;
	
	@OneToMany(mappedBy = "medico")
	@JsonManagedReference
	private List<Consulta> consultas;
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getPapel() {
		return papel;
	}
	
	public String getNome() {
		return nome;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCRM() {
		return CRM;
	}
	public void setCRM(String CRM) {
		this.CRM = CRM;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	public List<Consulta> getConsultas(){
		return consultas;
	}
	public void setConsulta(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}
