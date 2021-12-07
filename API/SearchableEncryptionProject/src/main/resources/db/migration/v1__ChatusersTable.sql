create table "CHATUSERS"
(
    id bigserial not null
        constraint user_pk
            primary key,
    username varchar(50) not null,
    password varchar(256) not null,
    email varchar(50) not null,
    type varchar(10) not null
);