package com.rltw.todo.notes.repository;

import com.rltw.todo.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findNotesByTodoId(Long todoId);
}
