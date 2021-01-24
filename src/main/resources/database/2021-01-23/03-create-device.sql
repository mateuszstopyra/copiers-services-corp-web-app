--liquibase formatted sql
--changeset mstopyra:3

create table copiers.devices
(
    id               bigint auto_increment
        primary key,
    color_page_price decimal(19, 2) null,
    lease_price      decimal(19, 2) null,
    mono_page_price  decimal(19, 2) null,
    serial_number    varchar(255)   null,
    address_id       bigint         null,
    customer_id      bigint         null,
    model_id         bigint         null,
    constraint UK_6ju48hv6y1f2kn982hyxd0wep
        unique (serial_number),
    constraint FKirc2ii289xtn4rf4hlaa3t0ul
        foreign key (model_id) references copiers.models (id),
    constraint FKjorv3lxggbpy2d1eer0ye890w
        foreign key (address_id) references copiers.addresses (id),
    constraint FKmemo5fyn0w6ownvxob5lcd4e9
        foreign key (customer_id) references copiers.customers (id)
);

