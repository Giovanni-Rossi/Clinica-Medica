package com.br.clinica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.br.clinica.dao.*;
import com.br.clinica.dao.IMedicoDAO;
import com.br.clinica.domain.Cliente;
import com.br.clinica.domain.Medico;
 
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IClienteDAO dao;

    @Autowired  
    private IMedicoDAO daoMedico;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Cliente cliente = dao.findByEmail(username);

        if (cliente != null) {
            return new ClienteDetails(cliente);
        }

        Medico medico = daoMedico.findByEmail(username);

        if (medico != null) {
            return new MedicoDetails(medico);
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
