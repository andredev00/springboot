 DROP ALL OBJECTS;

 DROP TABLE IF EXISTS registHome;
 DROP TABLE IF EXISTS user;
 DROP TABLE IF EXISTS roles;
 DROP TABLE IF EXISTS client;

CREATE TABLE client (id INT auto_increment, name VARCHAR(250) not null, email VARCHAR(250) not null, password VARCHAR(250) not null);

CREATE TABLE user(id INT auto_increment, name VARCHAR(250) not null, email VARCHAR(250) not null, password VARCHAR(250) not null);

CREATE TABLE home(id INT auto_increment, location varchar(250) not null, grossArea int, lotTotal int, room int, floor varchar(250), constructionYear int, wcs int, parking boolean, description varchar(255), homeType varchar(50), imagePath varchar(255), imageFileName varchar(255));

CREATE TABLE roles(id int auto_increment, name varchar(250));

INSERT INTO client (name, email, password) values ('André Ferreira', 'andreferrreira6578@gmail.com', '*Z!Ka<n5H~F[6^M}');

INSERT INTO user(name, email, password) values ('André Filipe', 'andrefilipe@gmail.com', '!AW2<gA`UF2>a2%%');