package com.rafaelsilva91.github.apikeysecurity.app.domain.entities;

import com.rafaelsilva91.github.apikeysecurity.app.domain.dtos.UserDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Users implements Serializable {

    private static final long serialversionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;
    private String password;

    public Users() {
    }

    public Users(Integer id, String login, String email, String password) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Users(UserDTO objDto) {
        this.id = objDto.getId();
        this.login = objDto.getLogin();
        this.email = objDto.getEmail();
        this.password = objDto.getPassword();

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        if (getId() != null ? !getId().equals(users.getId()) : users.getId() != null) return false;
        return getLogin() != null ? getLogin().equals(users.getLogin()) : users.getLogin() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        return result;
    }


}
