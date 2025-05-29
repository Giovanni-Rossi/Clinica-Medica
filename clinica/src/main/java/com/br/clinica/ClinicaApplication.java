package com.br.clinica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.br.clinica.dao.*;

import com.br.clinica.domain.Cliente;
import com.br.clinica.domain.Medico;

@SpringBootApplication
public class ClinicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IClienteDAO clienteDAO, IMedicoDAO medicoDAO) {
		return (args) -> {
			Cliente admin = new Cliente();
			admin.setEmail("admin@ufscar.br");
			admin.setSenha("admin");
			admin.setcpf("111.111.111-11");
			admin.setNome("admin");
			admin.setTelefone("(11) 11111-1111");
			admin.setSexo("Masculino");
			admin.setDataNascimento("01/01/2000");
			admin.setPapel("ROLE_ADMIN");
			admin.setEnabled(true);
			clienteDAO.save(admin);
			
			Medico medico = new Medico();
			medico.setNome("Luis");
			medico.setSenha("senha");
			medico.setEmail("luis@medico.br");
			medico.setEspecialidade("ORTOPEDISTA");
			medico.setPapel("ROLE_USER");
			medico.setCRM("0000000-0/BR");
			medico.setEnabled(true);
			medicoDAO.save(medico);
		};
		
	}

}
