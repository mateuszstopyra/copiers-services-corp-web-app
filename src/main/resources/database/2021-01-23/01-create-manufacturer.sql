--liquibase formatted sql
--changeset mstopyra:1

create table copiers.manufacturers
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null,
    constraint UK_rgruuf4bdtokowdxk169bs8op
        unique (name)
);

