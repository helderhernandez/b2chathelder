package com.b2chat.b2chathelder.adapters.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.b2chat.b2chathelder.domain.exceptions.NotFoundException;
import com.b2chat.b2chathelder.domain.models.User;
import com.b2chat.b2chathelder.domain.ports.UserPersistencePort;

@Repository
public class UserPersistenceAdapter implements UserPersistencePort {
	@Autowired
	private UserRepository userRepository;

	@Override
	public User create(String username, String password, String email) {
		//@formatter:off
		UserEntity userEntity = UserEntity.builder()
				.username(username)
				.password(password)
				.email(email)
				.isActive(true) // default init value
				.isDelete(false) // default init value
				.build();
		//@formatter:on

		userRepository.save(userEntity);

		return UserMapper.entityToDomain(userEntity);
	}

	@Override
	public boolean existsUsername(String username) {
		return userRepository.existsByUsernameAndIsDeleteFalse(username);
	}

	@Override
	public boolean existsEmail(String email) {
		return userRepository.existsByEmailAndIsDeleteFalse(email);
	}

	@Override
	public User findById(Long id) {
		UserEntity userEntity = userRepository.findByIdAndIsDeleteFalse(id).orElseThrow(() -> {
			return new NotFoundException("Not found user with id " + id);
		});

		return UserMapper.entityToDomain(userEntity);
	}

	@Override
	public User update(Long id, String username, String password, boolean isActive) {
		UserEntity userEntity = userRepository.getReferenceById(id);

		// NOTE: The email field is not considered for the update
		
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		userEntity.setActive(isActive);

		userRepository.save(userEntity);

		return UserMapper.entityToDomain(userEntity);
	}

	@Override
	public boolean existsById(Long id) {
		return userRepository.existsByIdAndIsDeleteFalse(id);
	}

	@Override
	public boolean usernameExistsWithAnotherId(Long id, String username) {
		return userRepository.existsByIdNotAndUsernameAndIsDeleteFalse(id, username);
	}

	@Override
	public void delete(Long id) {
		UserEntity userEntity = userRepository.getReferenceById(id);

		// mark as inactive and deleted
		userEntity.setActive(false);
		userEntity.setDelete(true);

		userRepository.save(userEntity);		
	}
}
