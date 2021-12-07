create table ACCOUNTS
(
    id bigserial not null
        constraint accounts_pk
            primary key,
    "creationDate" date not null,
    status int not null,
    user_id bigint not null
        constraint accounts_chatusers_id_fk
            references chatusers
);