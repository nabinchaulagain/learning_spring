--liquibase formatted sql
--changeset nabin:create_users_table:1 endDelimiter:; rollbackEndDelimeter:;
CREATE TABLE users(
    id SERIAL NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    PRIMARY KEY(id)
);
--rollback DROP TABLE users;
