-- script to reset table user

-- delete all records
DELETE FROM user;

-- re-set auto incremet value
ALTER TABLE user AUTO_INCREMENT=1;

-- data for test
INSERT INTO user (username, password, email, is_active, is_delete) VALUES 
	('juanito.johns', 'Juanito.2024', 'juanito@mail.com', true, false),
    ('laura.soriano', 'Juanito.2024', 'laura@mail.com', false, true),
    ('margarita.clement', 'margarita.2024', 'margarita@mail.com', false, false),
    ('cleopatra.vicente', 'cleopatra.2024', 'cleopatra@mail.com', true, false);