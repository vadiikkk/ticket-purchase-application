--liquibase formatted sql
--changeset Vadim Volovik:1
CREATE TABLE _session
(
    id      SERIAL PRIMARY KEY,
    user_id INT                         NOT NULL,
    token   VARCHAR(255)                NOT NULL,
    expires TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES _user (id)
);