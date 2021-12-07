create table "Messages"
(
    id bigserial not null
        constraint messages_pk
            primary key,
    sender varchar(50) not null,
    receiver varchar(50) not null,
    text varchar(1000) not null,
    read bit,
    "sentDate" date not null,
    user_id bigint not null
        constraint messages_chatusers_id_fk
            references chatusers,
    category varchar(50) not null
);