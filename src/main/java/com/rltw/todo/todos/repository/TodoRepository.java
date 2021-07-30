package com.rltw.todo.todos.repository;

import com.rltw.todo.auth.model.User;
import com.rltw.todo.todos.model.Todo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    public Optional<Todo> getTodoById(long id);

    public List<Todo> getTodosByUser(User user);
}
