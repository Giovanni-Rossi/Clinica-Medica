package com.br.clinica.security;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.clinica.domain.Cliente;
 
@SuppressWarnings("serial")
public class ClienteDetails implements UserDetails {
    private Cliente cliente;
     
    public ClienteDetails(Cliente cliente) {
        this.cliente = cliente;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(cliente.getPapel());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return cliente.getSenha();
    }
 
    @Override
    public String getUsername() {
        String username = cliente.getEmail();
        username = username.substring(0, username.indexOf('@'));
        return username;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

	public Cliente getCliente() {
		return cliente;
	}
}
