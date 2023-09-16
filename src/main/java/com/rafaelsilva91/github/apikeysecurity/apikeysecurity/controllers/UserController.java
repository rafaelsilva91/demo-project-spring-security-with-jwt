package com.rafaelsilva91.github.apikeysecurity.apikeysecurity.controllers;

import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.dtos.UserDTO;
import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.entities.User;
import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;
    private final PasswordEncoder encoder;

    public UserController(UserService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        User obj = service.findById(id);
        UserDTO objDTO = new UserDTO(obj);
        return ResponseEntity.ok().body(objDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objDto) {
        //System.out.println("senha: "+objDto.getPassword());
        objDto.setPassword(encoder.encode(objDto.getPassword()));
        //System.out.println("senha encriptada: "+objDto.getPassword());
        User usuario = service.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/validaSenha")
    public ResponseEntity<Boolean> validPassword(@RequestParam String login,
                                                 @RequestParam String password) {

        User usuario = service.findByLogin(login);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = false;
        valid = encoder.matches(password, usuario.getPassword());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

}