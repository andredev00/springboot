DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS registHome;

CREATE TABLE client (name VARCHAR(250),password VARCHAR(250),username VARCHAR(250));

INSERT INTO client (name, password, username) VALUES
    ('Andre', 'qwerjhbqwer', 'andrefr770');

CREATE TABLE user(name VARCHAR(250), username VARCHAR(250),password VARCHAR(250));

INSERT INTO user(name, username, password) VALUES
    ('João', 'joao', 'wenrjnqwej');