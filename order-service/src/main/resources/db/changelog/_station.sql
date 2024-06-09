--liquibase formatted sql
--changeset Vadim Volovik:1
CREATE TABLE _station
(
    id      SERIAL PRIMARY KEY,
    station VARCHAR(50) NOT NULL
);

INSERT INTO _station (station)
VALUES ('Station 1'),
       ('Station 2'),
       ('Station 3'),
       ('Station 4'),
       ('Station 5'),
       ('Station 6'),
       ('Station 7'),
       ('Station 8'),
       ('Station 9'),
       ('Station 10');