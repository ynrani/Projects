CREATE TABLESPACE tdg_users 
   DATAFILE 'tdg_users.dat' SIZE 40M 
   ONLINE; 
   
   
   CREATE TABLESPACE tdg_gui
   DATAFILE 'tdg_gui.dat' SIZE 750M 
   ONLINE; 
   
   create user usres IDENTIFIED BY usres default TABLESPACE tdg_users quota 20m on tdg_users;
   grant create session to usres;
   grant create table to usres;
   grant create sequence to usres;


--CREATE DATABASE usres USER sys IDENTIFIED BY usres USER SYSTEM IDENTIFIED BY users EXTENT MANAGEMENT LOCAL DEFAULT TABLESPACE tdg_users;


create user tdg IDENTIFIED BY tdg default TABLESPACE tdg_gui quota 750m on tdg_gui;
   grant create session to tdg;
   grant create table to tdg;
   grant create sequence to tdg;


--CREATE DATABASE tdg USER sys IDENTIFIED BY tdg USER SYSTEM IDENTIFIED BY tdg EXTENT MANAGEMENT LOCAL DEFAULT TABLESPACE tdg_gui;


 CREATE TABLESPACE tdg_idfc
   DATAFILE 'tdg_idfc.dat' SIZE 750M 
   ONLINE; 
   
   create user idfc IDENTIFIED BY idfc default TABLESPACE tdg_idfc quota 750m on tdg_idfc;
   grant create session to idfc;
   grant create table to idfc;
   grant create sequence to idfc;