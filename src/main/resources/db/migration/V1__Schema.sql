create table citizenship
(
    id   int not null auto_increment,
    name varchar(1024) default null,
    primary key (id)
);

create table city
(
    id   int           not null auto_increment,
    name varchar(1024) not null,
    primary key (id)
);

create table disability
(
    id          int           not null auto_increment,
    name        varchar(1024) not null,
    description varchar(2048) default null,
    primary key (id)
);

create table marital_status
(
    id     int           not null auto_increment,
    status varchar(1024) not null,
    primary key (id)
);

create table passport
(
    id                  int           not null auto_increment,
    id_number           varchar(128)  not null,
    passport_series     varchar(8)    not null,
    passport_number     varchar(16)   not null,
    passport_issuer     varchar(2048) not null,
    passport_issue_date date          not null,
    primary key (id),
    unique key passport_passport_series_passport_number_uindex (passport_series, passport_number),
    unique key passport_id_number_uindex (id_number)
);

create table client
(
    id                      int                    not null auto_increment,
    passport_id             int           default null,
    first_name              varchar(256)           not null,
    second_name             varchar(256)           not null,
    patronymic              varchar(256)           not null,
    birth_date              date                   not null,
    birth_place             varchar(1024)          not null,
    gender                  enum ('MALE','FEMALE') not null,
    live_city_id            int                    not null,
    live_address            varchar(2048)          not null,
    phone_home              varchar(128)  default null,
    phone_mobile            varchar(128)  default null,
    email                   varchar(1024) default null,
    residence_city_id       int                    not null,
    residence_address       varchar(1024)          not null,
    marital_status_id       int                    not null,
    citizenship_id          int                    not null,
    disability_id           int                    not null,
    retiree                 tinyint(1)             not null,
    monthly_income_value    int           default null,
    monthly_income_currency varchar(8)    default null,
    primary key (id),
    unique key client_passport_id_uindex (passport_id),
    key client_city_id_fk (live_city_id),
    key client_city_id_fk_2 (residence_city_id),
    key client_marital_status_id_fk (marital_status_id),
    key client_citizenship_id_fk (citizenship_id),
    key client_disability_id_fk (disability_id),
    constraint client_citizenship_id_fk foreign key (citizenship_id) references citizenship (id) on update cascade,
    constraint client_city_id_fk foreign key (live_city_id) references city (id) on update cascade,
    constraint client_city_id_fk_2 foreign key (residence_city_id) references city (id) on update cascade,
    constraint client_disability_id_fk foreign key (disability_id) references disability (id) on update cascade,
    constraint client_marital_status_id_fk foreign key (marital_status_id) references marital_status (id) on update cascade,
    constraint client_passport_id_fk foreign key (passport_id) references passport (id) on update cascade
);