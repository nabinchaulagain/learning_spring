package com.rltw.todo.notes.service;

import com.rltw.todo.notes.model.Note;
import com.rltw.todo.notes.repository.NoteRepository;
import com.rltw.todo.todos.model.Todo;
import com.rltw.todo.todos.service.TodoService;
import com.rltw.todo.util.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    TodoService todoService;

    public Note getNoteById(long noteId){
        Optional<Note> optionalNote = noteRepository.findById(noteId);

        if(optionalNote.isEmpty()){
            throw new EntityNotFoundException("Note");
        }

        return optionalNote.get();
    }

    public Note addNote(long todoId, Note payload){
        payload.setTodoId(todoId);
        return noteRepository.save(payload);
    }

    public Note editNote(long id, Note payload){
        Note note = getNoteById(id);
        note.setNote(payload.getNote());

        return noteRepository.save(note);
    }

    public void deleteTodo(Long id){
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }

    public List<Note> getNotesByTodoId(long todoId){
        return noteRepository.findNotesByTodoId(todoId);
    }
}
