drop table if exists users;

create table users (
  id integer,
  name varchar(20)
);

insert into users (id, name) values(1, 'User1');
insert into users (id, name) values(2, 'User2');
