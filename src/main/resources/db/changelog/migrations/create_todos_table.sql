--liquibase formatted sql
--changeset nabin:create_todos_table:1 endDelimiter:; rollbackEndDelimiter:;
CREATE TABLE todos(
    id SERIAL NOT NULL,
    title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INTEGER NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(id),
    CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id)
);
--rollback DROP table todos;