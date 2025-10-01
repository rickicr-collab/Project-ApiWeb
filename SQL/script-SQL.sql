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
insert into usuarios values(null,"Rafael Cunha", "Rafael@email.com", "senhaRafael", "8188617005");
insert into usuarios values(null, "ZIna Cunha", "Zina@email.com", "senhaZina", "8188973917");

-- visualizando a tabela --
select * from usuarios;

-- deletando uma coluna --
Alter table usuarios Drop column username;

-- metodo union --
Select nome_completo, email, senha, telefone from usuarios where id = 1
union  select nome_completo,email,senha, telefone from usuarios where id = 2;