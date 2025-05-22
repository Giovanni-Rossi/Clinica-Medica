package com.br.clinica.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="cliente")

public class Cliente extends AbstractEntity<Long> {
	@NotBlank
	@Column(nullable = false, length = 30)
	private String email;
	
	@Column(nullable = false, length = 30)
	private String senha;
	
	@NotBlank
	@Column(nullable = false, length = 14)
	private String cpf;
	
	@NotBlank
	@Column(nullable = false, length = 30)
	private String nome;
	
	@Column(nullable = false, length = 20)
	private String telefone;
	
	@Column(nullable = false, length = 20)
	private String sexo;
	
	@NotBlank 
	@Column(nullable = false,  length = 10)
	private String dataNascimento;
	
	@NotBlank
	@Column(nullable = false, length = 10)
	private String papel;
	
	@Column(nullable=false)
    private boolean enabled;
	
	@OneToMany(mappedBy = "cliente")
	@JsonManagedReference
	private List<Consulta> consultas;
	
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
	
	public String getcpf() {
		return cpf;	
	}
	
	public void setcpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public List<Consulta> getConsultas(){
		return consultas;
	}
	public void setConsulta(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
	
}
