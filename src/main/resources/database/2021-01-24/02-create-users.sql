-- liquibase formatted sql
-- changeset mstopyra:1


create table copiers.users
(
    id       bigint auto_increment
        primary key,
    username varchar(50)  not null,
    password varchar(100) not null,
    enabled  tinyint(1)   not null,
    constraint username
        unique (username)
);

