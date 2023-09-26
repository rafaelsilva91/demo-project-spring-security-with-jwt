package com.rafaelsilva91.github.apikeysecurity.app.domain.services;

import com.rafaelsilva91.github.apikeysecurity.app.data.UserDetailsClient;
import com.rafaelsilva91.github.apikeysecurity.app.domain.entities.Users;
import com.rafaelsilva91.github.apikeysecurity.app.domain.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsClientService implements UserDetailsService{

    private final UserService service;

    public UserDetailsClientService(UserService service) {
        this.service = service;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> usuario = Optional.ofNullable(service.findByLogin(username));

        if(usuario.isEmpty()){
            throw new ObjectNotFoundExceptions("Usuário ["+username+"] não encontrado!");
        }

        return new UserDetailsClient(usuario);
    }
}
