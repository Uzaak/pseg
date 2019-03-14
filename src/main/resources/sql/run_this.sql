create database pseg;

use pseg;

create table `credit_card` (
    id int primary key auto_increment,
    card_number bigint,
    holder_id bigint,
    holder varchar(255),
    cvv int,
    expiration date
);

create table `product` (
    id int primary key auto_increment,
    description varchar(255),
    price decimal(10,2)
);

create table `user` (
    id int primary key auto_increment,
    name varchar(255),
    cpf bigint,
    email varchar(255),
    password varchar(255)
);

create table transaction (
    id int primary key auto_increment,
    user_id bigint,
    credit_card_number bigint,
    credit_card_holder varchar(255),
    product_id bigint,
    paid bit
);
