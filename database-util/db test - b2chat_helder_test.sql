-- re-create database
DROP DATABASE IF EXISTS b2chat_helder_test;
CREATE DATABASE b2chat_helder_test;

-- creating user for API
DROP USER IF EXISTS 'user_b2chat_helder_test'@'localhost';
CREATE USER 'user_b2chat_helder_test'@'localhost' IDENTIFIED BY 'apipasswordtest';

-- granting permissions to the user
GRANT ALL PRIVILEGES ON b2chat_helder_test.* TO 'user_b2chat_helder_test'@'localhost';

USE b2chat_helder_test;

-- create table user
CREATE TABLE user (
	id BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(25) NOT NULL,
    password VARCHAR(60) NOT NULL, -- encriypted by API
    email VARCHAR(250) NOT NULL,
    is_active TINYINT DEFAULT 1 NOT NULL,
    is_delete TINYINT DEFAULT 1 NOT NULL,
    
    -- constraints
    CONSTRAINT pk_user PRIMARY KEY (id),
    
    -- business rules: username and email must be unique
    CONSTRAINT uqc_user_username UNIQUE (username, is_delete),
    CONSTRAINT uqc_user_email UNIQUE (email, is_delete)
);
