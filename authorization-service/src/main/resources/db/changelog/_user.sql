--liquibase formatted sql
--changeset Vadim Volovik:1
CREATE TABLE _user
(
    id       SERIAL PRIMARY KEY,
    nickname VARCHAR(50) UNIQUE  NOT NULL,
    email    VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    created  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);