package com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.services;

import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.dtos.UserDTO;
import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.entities.User;
import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.repositories.IUserRepository;
import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.services.exceptions.DataIntegrityViolationException;
import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll(){
        List<User> list = repository.findAll();
        return list;
    }

    public User create(UserDTO objDto) {
        validacaoUsuario(objDto);
        User usuario = new User(objDto);
        return repository.save(usuario);
    }

    private void validacaoUsuario(UserDTO objDTO) {
        Optional<User> obj =  repository.findByLogin(objDTO.getLogin());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Login já cadastrado no sistema!");
        }

        obj = repository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

    public User findById(Integer id) {
        Optional<User> usuario = repository.findById(id);
        return usuario.orElseThrow(()->new ObjectNotFoundExceptions("Usuario não encontrado!"));

    }

    public User findByLogin(String login){
        Optional<User> usuario = repository.findByLogin(login);
        return usuario.orElseThrow(()->new ObjectNotFoundExceptions("Usuario não encontrado!"));
    }

}
