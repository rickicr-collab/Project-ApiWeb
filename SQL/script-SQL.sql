create database DBUsuarios;
use DBUsuarios;

create table usuarios(
id            integer auto_increment primary key,
nome_completo varchar(200) not null,
username      varchar(100) not null unique,
email         varchar(50) not null unique,
senha         text not null,
telefone      varchar(15) not null);

-- inserindo dados na tabela --
insert into usuarios values(null, "Ricardo Cunha", "Ricardo10", "ricardo@email.com", "senhaRicardo", "8197400404");

-- visualizando a tabela --
select * from usuarios;