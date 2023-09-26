package com.rafaelsilva91.github.apikeysecurity.app.domain.services;

import com.rafaelsilva91.github.apikeysecurity.app.domain.dtos.UserDTO;
import com.rafaelsilva91.github.apikeysecurity.app.domain.entities.Users;
import com.rafaelsilva91.github.apikeysecurity.app.domain.repositories.IUserRepository;
import com.rafaelsilva91.github.apikeysecurity.app.domain.services.exceptions.DataIntegrityViolationException;
import com.rafaelsilva91.github.apikeysecurity.app.domain.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public List<Users> findAll(){
        List<Users> list = repository.findAll();
        return list;
    }

    public Users create(UserDTO objDto) {
        validacaoUsuario(objDto);
        Users usuario = new Users(objDto);
        return repository.save(usuario);
    }

    private void validacaoUsuario(UserDTO objDTO) {
        Optional<Users> obj =  repository.findByLogin(objDTO.getLogin());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Login já cadastrado no sistema!");
        }

        obj = repository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

    public Users findById(Integer id) {
        Optional<Users> usuario = repository.findById(id);
        return usuario.orElseThrow(()->new ObjectNotFoundExceptions("Usuario não encontrado!"));

    }

    public Users findByLogin(String login){
        Optional<Users> usuario = repository.findByLogin(login);
        return usuario.orElseThrow(()->new ObjectNotFoundExceptions("Usuario não encontrado!"));
    }

}
