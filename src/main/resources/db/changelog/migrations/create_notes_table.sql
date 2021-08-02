--liquibase formatted sql
--changeset nabin:create_notes_table:1 endDelimiter:; rollbackEndDelimiter:;
CREATE TABLE notes(
                      id SERIAL NOT NULL,
                      note VARCHAR(255) NOT NULL,
                      todo_id INTEGER NOT NULL,
                      PRIMARY KEY(id),
                      CONSTRAINT fk_todos FOREIGN KEY(todo_id) REFERENCES todos(id)
);
--rollback DROP table notes;