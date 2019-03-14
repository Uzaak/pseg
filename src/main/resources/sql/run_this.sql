create database tiago;

use tiago;

create table credit_card (
    id primary key auto_increment,
    card_number bigint,
    holder_id bigint,
    holder varchar(255),
    cvv int,
    expiration date
);

create table product (
    id primary key auto_increment,
    description varchar(255),
    price decimal(10,2)
);

create table user (
    id primary key auto_increment,
    name varchar(255),
    cpf bigint,
    email varchar(255),
    password varchar(255)
)