package com.rltw.todo.notes.model;

import com.rltw.todo.todos.model.Todo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="note")
    @NotNull(message = "Note can't be empty")
    @Size(min=2,max=255,message="Note must be between 2-255 chars")
    private String note;

    @Column(name="todo_id",updatable = false)
    private long todoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", todoId=" + todoId +
                '}';
    }
}
