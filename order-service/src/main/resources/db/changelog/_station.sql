--liquibase formatted sql
--changeset Vadim Volovik:1
CREATE TABLE _station
(
    id      SERIAL PRIMARY KEY,
    station VARCHAR(50) NOT NULL
);
