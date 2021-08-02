package com.rltw.todo.todos.controller;

import com.rltw.todo.auth.model.User;
import com.rltw.todo.todos.model.Todo;
import com.rltw.todo.todos.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/todos/")
@RestController()
public class TodoController {
    @Autowired
    TodoService todoService;

    @GetMapping()
    List<Todo> getTodos(@AuthenticationPrincipal User user){
        return todoService.getTodos(user);
    }

    @PostMapping()
    Todo addTodo(@AuthenticationPrincipal User user, @RequestBody @Valid Todo payload){
        return todoService.addTodo(payload,user);
    }

    @PatchMapping(path="{id}")
    Todo editTodo(@AuthenticationPrincipal User user,@PathVariable long id,@RequestBody @Valid Todo payload){
        return todoService.editTodo(id,payload,user);
    }

    @GetMapping(path="{id}")
    Todo getTodo(@AuthenticationPrincipal User user, @PathVariable long id){
        Todo todo =  todoService.getTodo(id);

        todoService.authorizeTodo(todo,user);
        return todo;
    }

    @DeleteMapping(path="{id}")
    String deleteTodo(@AuthenticationPrincipal User user,@PathVariable long id){
        todoService.deleteTodo(id,user);

        return "Successfully deleted todo.";
    }
}
