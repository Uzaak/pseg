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

