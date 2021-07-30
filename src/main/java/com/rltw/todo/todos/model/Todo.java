package com.rltw.todo.todos.model;

import com.rltw.todo.auth.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name="todos")
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @NotEmpty
    @Size(min = 3,max=255, message = "Title must be between 3 and 255 chars")
    @Column(name="title")
    private String title;

    @Column(name="is_completed")
    private boolean isCompleted = false;

    @Column(name="created_at",insertable = false,updatable = false)
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isCompleted=" + isCompleted +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
