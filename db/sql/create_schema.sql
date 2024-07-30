-- Configuracion propuesta por spring

--create table users(
--    username varchar(50) not null primary key,
--    password varchar(500) not null,
--    enabled boolean not null
--);
--
--create table authorities (
--    username varchar(50) not null,
--    authority varchar(50) not null,
--    constraint fk_authorities_users foreign key(username) references users(username)
--);
--
--create unique index ix_auth_username on authorities (username, authority);

-- Nuestra propia configuracion de DB
create table customers(
  id bigserial primary key,
  email varchar(50) not null,
  pwd varchar(500) not null,
  rol varchar(20) not null
);