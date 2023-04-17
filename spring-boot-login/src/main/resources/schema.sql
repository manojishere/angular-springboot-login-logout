create table user
(
   id integer not null,
   username varchar(255) not null,
   firstname varchar(255) not null,
   lastname varchar(255) not null,
   password varchar(255) not null,
   email varchar(255),
   phonenumber long,
   primary key(id)
);