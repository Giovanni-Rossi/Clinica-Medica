package com.br.clinica.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name="Consulta")
public class Consulta extends AbstractEntity<Long> {
	@Column(nullable=false, length = 20)
	private String data;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	@JsonBackReference
	private Cliente cliente;
	
	@NotNull(message="Não pode haver consulta sem médico")
	@ManyToOne
	@JoinColumn(name="medico_id")
	@JsonBackReference
	private Medico medico;
	
	public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Medico getMedico() {
    	return medico;
    }
    
    public void setMedico(Medico Medico) {
    	this.medico = Medico;
    }
}
