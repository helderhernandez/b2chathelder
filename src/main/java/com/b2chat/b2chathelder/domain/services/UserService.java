package com.b2chat.b2chathelder.domain.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.b2chat.b2chathelder.domain.dto.UserCreateInput;
import com.b2chat.b2chathelder.domain.dto.UserUpdateInput;
import com.b2chat.b2chathelder.domain.exceptions.NotFoundException;
import com.b2chat.b2chathelder.domain.exceptions.UniqueConstraintException;
import com.b2chat.b2chathelder.domain.models.User;
import com.b2chat.b2chathelder.domain.ports.UserPersistencePort;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class UserService {
	@Autowired
	private UserPersistencePort userPersistencePort;

	@Autowired
	private Validator validator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(rollbackFor = Exception.class)
	public User create(UserCreateInput userCreateInput) {
		// validate form fields
		Set<ConstraintViolation<UserCreateInput>> violations = validator.validate(userCreateInput);

		if (violations.isEmpty() == false) {
			throw new ConstraintViolationException("Invalid form data", violations);
		}

		// validate that the username is unique
		final String USERNAME = userCreateInput.getUsername();
		if (userPersistencePort.existsUsername(USERNAME)) {
			throw new UniqueConstraintException("Username " + USERNAME + " already exists");
		}

		// validate that the email is unique
		final String EMAIL = userCreateInput.getEmail();
		if (userPersistencePort.existsEmail(EMAIL)) {
			throw new UniqueConstraintException("Email " + EMAIL + " already exists");
		}

		// encryp password
		String password = passwordEncoder.encode(userCreateInput.getPassword());

		return userPersistencePort.create(USERNAME, password, EMAIL);
	}

	@Transactional(readOnly = true)
	public User read(Long id) {
		return userPersistencePort.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public User update(Long id, UserUpdateInput userUpdateInput) {
		// validate form fields
		Set<ConstraintViolation<UserUpdateInput>> violations = validator.validate(userUpdateInput);

		if (violations.isEmpty() == false) {
			throw new ConstraintViolationException("Invalid form data", violations);
		}

		// verify that the record exists
		if (userPersistencePort.existsById(id) == false) {
			throw new NotFoundException("Not found user with id " + id);
		}

		// verify that the username is not being used by another registry
		final String USERNAME = userUpdateInput.getUsername();
		if (userPersistencePort.usernameExistsWithAnotherId(id, USERNAME)) {
			throw new UniqueConstraintException("Username " + USERNAME + " is already used by another registration");
		}

		// encryp password
		String password = passwordEncoder.encode(userUpdateInput.getPassword());

		return userPersistencePort.update(id, USERNAME, password, userUpdateInput.getIsActive());
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		// verify that the record exists
		if (userPersistencePort.existsById(id) == false) {
			throw new NotFoundException("Not found user with id " + id);
		}

		userPersistencePort.delete(id);
	}

}
