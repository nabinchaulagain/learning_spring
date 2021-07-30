package com.rltw.todo.auth.repository;

import com.rltw.todo.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthUserRepository extends JpaRepository<User,Long>
{
    public boolean existsByUsername(String username);

    public Optional<User> getUserByUsername(String username);
}
