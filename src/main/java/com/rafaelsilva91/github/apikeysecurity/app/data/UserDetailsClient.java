package com.rafaelsilva91.github.apikeysecurity.app.data;

import com.rafaelsilva91.github.apikeysecurity.app.domain.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UserDetailsClient implements UserDetails {

    private final Optional<Users> usuario;

    public UserDetailsClient(Optional<Users> usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return this.usuario.orElse(new Users()).getPassword();
    }

    @Override
    public String getUsername() {
        return this.usuario.orElse(new Users()).getLogin();
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
}
