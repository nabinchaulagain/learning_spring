package com.rltw.todo.todos.service;

import com.rltw.todo.auth.model.User;
import com.rltw.todo.todos.model.Todo;
import com.rltw.todo.todos.repository.TodoRepository;
import com.rltw.todo.util.exception.EntityNotFoundException;
import com.rltw.todo.util.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo getTodo(long id) throws EntityNotFoundException{
        Optional<Todo> optionalTodo =  todoRepository.getTodoById(id);
        if(optionalTodo.isEmpty()){
            throw new EntityNotFoundException("Todo");
        }

        return optionalTodo.get();
    }

    public void authorizeTodo(Todo todo,User user){
        if(user != todo.getUser()){
            throw new UnauthorizedException();
        }
    }

    public List<Todo> getTodos(User user){
        return todoRepository.getTodosByUser(user);
    }

    public Todo addTodo(Todo todoPayload, User user){
        todoPayload.setUser(user);
        return todoRepository.save(todoPayload);
    }

    public Todo editTodo(long id, Todo todoPayload, User user){
        Todo todo = this.getTodo(id);

        this.authorizeTodo(todo,user);

        todo.setCompleted(todoPayload.isCompleted());
        todo.setTitle(todoPayload.getTitle());

        return todoRepository.save(todo);
    }

    public void deleteTodo(long id,User user){
        Todo todo = this.getTodo(id);

        this.authorizeTodo(todo,user);

         todoRepository.delete(todo);
    }

}
