 DROP ALL OBJECTS;

 DROP TABLE IF EXISTS registHome;
 DROP TABLE IF EXISTS user;
 DROP TABLE IF EXISTS roles;
 DROP TABLE IF EXISTS client;

CREATE TABLE client (id INT auto_increment, name VARCHAR(250) not null, email VARCHAR(250) not null, password VARCHAR(250) not null);

CREATE TABLE user(id INT auto_increment, name VARCHAR(250) not null, email VARCHAR(250) not null, password VARCHAR(250) not null);

CREATE TABLE home(id INT auto_increment, location varchar(250) not null, gross_Area int, lot_Total int, room int, floor varchar(250), construction_Year int, wcs int, parking boolean);

CREATE TABLE roles(id int auto_increment, name varchar(250));
