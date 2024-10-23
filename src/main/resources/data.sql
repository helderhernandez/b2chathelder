-- script to reset table user

-- delete all records
DELETE FROM user;

-- re-set auto incremet value
ALTER TABLE user AUTO_INCREMENT=1;

-- data for test
-- password: 1234.ABcd
INSERT INTO user (username, password, email, is_active, is_delete) VALUES 
	('juanito.johns', '$2a$10$ulXZJwNF6N8/nwyhnJRni.lOr/1KOiqvNgB8Bk9ZdGIwKIyGHIS3i', 'juanito@mail.com', true, false),
    ('laura.soriano', '$2a$10$ulXZJwNF6N8/nwyhnJRni.lOr/1KOiqvNgB8Bk9ZdGIwKIyGHIS3i', 'laura@mail.com', false, true),
    ('margarita.clement', '$2a$10$ulXZJwNF6N8/nwyhnJRni.lOr/1KOiqvNgB8Bk9ZdGIwKIyGHIS3i', 'margarita@mail.com', false, false),
    ('cleopatra.vicente', '$2a$10$ulXZJwNF6N8/nwyhnJRni.lOr/1KOiqvNgB8Bk9ZdGIwKIyGHIS3i', 'cleopatra@mail.com', true, false);