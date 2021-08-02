package com.rltw.todo.notes.controller;

import com.rltw.todo.notes.model.Note;
import com.rltw.todo.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping()
    List<Note> getNotesByTodoId(@RequestParam("todoId") long todoId){
        return noteService.getNotesByTodoId(todoId);
    }

    @PostMapping()
    Note createNote(@RequestParam("todoId") long todoId,@RequestBody @Valid Note payload){
        return noteService.addNote(todoId,payload);
    }

    @PatchMapping("/{id}")
    Note editNote( @RequestBody @Valid Note payload,@PathVariable("id") long id){
        return noteService.editNote(id,payload);
    }

    @DeleteMapping("/{id}")
    String deleteNote(@PathVariable("id") long id){
         noteService.deleteTodo(id);
         return "Todo deleted successfully.";
    }
}
