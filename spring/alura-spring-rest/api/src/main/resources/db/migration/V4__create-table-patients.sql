CREATE TABLE patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(14) not null unique,
    street varchar(200) not null,
    neighborhood varchar(100) not null,
    zip varchar(9) not null,
    complement varchar(100) not null,
    number varchar(20) not null,
    state char(2) not null,
    city varchar(100) not null,
    phone varchar(20) not null,
    active tinyint not null,

    primary key(id)
)