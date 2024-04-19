create table if not exists users
(
    id            uuid        not null
        primary key,
    username      varchar(8)  not null,
    email_address varchar(50) not null,
    password      varchar(50) not null
);

alter table users
    owner to postgres;

create table if not exists merchant
(
    id                uuid        not null
        primary key,
    merchant_name     varchar(50) not null,
    merchant_location varchar(50) not null,
    open              boolean     not null
);

alter table merchant
    owner to postgres;

create table if not exists product
(
    id           uuid        not null
        primary key,
    product_name varchar(50) not null,
    price        numeric     not null,
    merchant_id  uuid        not null
        constraint fk_merchant
            references merchant
);

alter table product
    owner to postgres;

create table if not exists "order"
(
    id                  uuid         not null
        primary key,
    order_time          timestamp    not null,
    destination_address varchar(256) not null,
    user_id             uuid         not null
        constraint fk_users
            references users,
    completed           boolean      not null
);

alter table "order"
    owner to postgres;

create table if not exists order_detail
(
    id          uuid    not null
        primary key,
    product_id  uuid    not null
        constraint fk_product
            references product,
    order_id    uuid    not null
        constraint fk_order
            references "order",
    quantity    integer not null,
    total_price numeric not null
);

alter table order_detail
    owner to postgres;


