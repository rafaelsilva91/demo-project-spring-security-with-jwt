package com.rafaelsilva91.github.apikeysecurity.app.domain.repositories;

import com.rafaelsilva91.github.apikeysecurity.app.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Users, Integer> {

    public Optional<Users> findByLogin(String login);
    public Optional<Users> findByEmail(String email);
    public Optional<Users> findByPassword(String password);
}
