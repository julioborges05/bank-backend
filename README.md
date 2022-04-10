# bank-backend

## Steps to replicate the environment

1- Create a docker container with this command on the terminal < docker run --name bank-database -p 5432:5432 -e POSTGRES_PASSWORD=1234 -d postgres >

2- docker exec -it bank-database /bin/bash

3- psql postgres postgres

4- create database bank;

5- ctrl + d

6- psql bank postgres;

7-  
create table account_holder(  
    id serial primary key,  
    name varchar(200) not null,  
    cpf varchar(11) not null,  
    birthday date not null,  
    unique (cpf)  
);

8-  
create table agency(  
    id serial primary key,  
    name varchar(200) not null,  
    address varchar(500) not null  
);