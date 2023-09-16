package com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.dtos;

import com.rafaelsilva91.github.apikeysecurity.apikeysecurity.domain.entities.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final long serialversionUID = 1L;

    private Integer id;

    @NotNull(message = "O campo login é requerido")
    private String login;

    @NotNull(message = "O campo email é requerido")
    private String email;

    @NotNull(message = "O campo password é requerido")
    private String password;

    public UserDTO() {
    }

    public UserDTO(User obj) {
        this.id = obj.getId();
        this.login = obj.getLogin();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}