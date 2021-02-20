create schema bank;

create table citizenship
(
    id   int auto_increment
        primary key,
    name varchar(1024) null
);

create table city
(
    id   int auto_increment
        primary key,
    name varchar(1024) not null
);

create table disability
(
    id          int auto_increment
        primary key,
    name        varchar(1024) not null,
    description varchar(2048) null
);

create table marital_status
(
    id   int auto_increment
        primary key,
    name varchar(1024) not null
);

create table client
(
    id                      int auto_increment
        primary key,
    id_number               int                        not null,
    first_name              varchar(256)               not null,
    second_name             varchar(256)               not null,
    patronymic              varchar(256)               not null,
    birth_date              date                       not null,
    birth_place             varchar(1024)              not null,
    gender                  enum ('M', 'F')            not null,
    passport_series         varchar(512)               not null,
    passport_number         varchar(512)               not null,
    passport_issuer         varchar(2048)              not null,
    passport_issue_date     date                       not null,
    live_city               int                        not null,
    live_address            varchar(2048)              not null,
    phone_home              varchar(128)               null,
    phone_mobile            varchar(128)               null,
    email                   varchar(1024)              null,
    residence_city          int                        not null,
    marital_status          int                        not null,
    citizenship             int                        not null,
    disability              int                        not null,
    retiree                 tinyint(1)                 not null,
    monthly_income_value    int                        null,
    monthly_income_currency enum ('USD', 'BYN', 'RUB') null,
    constraint client_id_number_uindex
        unique (id_number),
    constraint client_citizenship_id_fk
        foreign key (citizenship) references citizenship (id)
            on update cascade,
    constraint client_city_id_fk
        foreign key (live_city) references city (id)
            on update cascade,
    constraint client_city_id_fk_2
        foreign key (residence_city) references city (id)
            on update cascade,
    constraint client_disability_id_fk
        foreign key (disability) references disability (id)
            on update cascade,
    constraint client_marital_status_id_fk
        foreign key (marital_status) references marital_status (id)
            on update cascade
);

