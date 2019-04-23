create table dictionary (
  id integer primary key auto_increment,
  name varchar(100) not null unique
);

create table test (
  id integer primary key auto_increment,
  name varchar(100) not null unique ,
  dict_id integer not null,
  constraint co foreign key fk(dict_id) references dictionary(id) on delete cascade on update cascade
);