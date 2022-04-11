1- Create a docker container with this command on the terminal `docker run --name bank-database -p 5432:5432 -e POSTGRES_PASSWORD=1234 -d postgres `

2- `docker exec -it bank-database /bin/bash`

3- `psql postgres postgres`

4- `create database bank;`

5- ctrl + d

6- `psql bank postgres;`

7-  
>create table account_holder(  
>id serial primary key,  
>name varchar(200) not null,  
>cpf varchar(11) not null,  
>birthday date not null,  
>unique (cpf)  
>);

8-  
>create table agency(  
>id serial primary key,  
>name varchar(200) not null,  
>address varchar(500) not null  
>);

9-
>create table checking_account(  
>id serial primary key,  
>id_account_holder int,  
>id_agency int,  
>account_limit float,  
>balance float,  
>is_active char(1),  
>foreign  key(id_account_holder) references account_holder(id),  
>foreign  key(id_agency) references agency(id)  
>);

10- Insert the datas on the database:
>insert into  
>public.account_holder (name, cpf, birthday)  
>values  
>('Júlio', '123456', '2001-11-30'),  
>('Carlos', '654321', '2000-10-20');

>insert into  
>public.agency (name, address)  
>values  
>('Itau', 'Rua A'),  
>('Bradesco', 'Rua B');

>insert into  
>public.checking_account (id_account_holder,id_agency,account_limit,balance,is_active)  
>VALUES  
>((select id from public.account_holder where name = 'Carlos'), (select id from public.agency where name = 'Itau'),100.0,100.0,'T');
	