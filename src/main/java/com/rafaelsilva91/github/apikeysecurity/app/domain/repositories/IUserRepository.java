package com.rafaelsilva91.github.apikeysecurity.app.domain.repositories;

import com.rafaelsilva91.github.apikeysecurity.app.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByLogin(String login);
    public Optional<User> findByEmail(String email);
}
