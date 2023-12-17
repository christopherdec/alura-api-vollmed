create table client
(
    id          bigint       not null auto_increment,
    name        varchar(100) not null,
    email       varchar(100) not null unique,
    phone       varchar(20)  not null unique,
    cpf         varchar(11)  not null unique,
    logradouro  varchar(100) not null,
    bairro      varchar(100) not null,
    cep         varchar(9)   not null,
    cidade      varchar(100) not null,
    uf          char(2)      not null,
    numero      varchar(20),
    complemento varchar(100),
    primary key (id)
)