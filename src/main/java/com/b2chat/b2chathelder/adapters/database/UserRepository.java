package com.b2chat.b2chathelder.adapters.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	/**
	 * Check if the username exists (only considers records that are not marked for
	 * deletion)
	 * 
	 * @param username
	 * @return true or false
	 */
	boolean existsByUsernameAndIsDeleteFalse(String username);

	/**
	 * Check if the email exists (only considers records that are not marked for
	 * deletion)
	 * 
	 * @param email
	 * @return true or false
	 */
	boolean existsByEmailAndIsDeleteFalse(String email);

	/**
	 * Recover a user by their id and that is not marked as deleted
	 * 
	 * @param id
	 * @return user entity
	 */
	Optional<UserEntity> findByIdAndIsDeleteFalse(Long id);

	/**
	 * Check if the id exists (only considers records that are not marked for
	 * deletion)
	 * 
	 * @param id
	 * @return true or false
	 */
	boolean existsByIdAndIsDeleteFalse(Long id);

	/**
	 * Check if the username exists (only considers records that are not marked for
	 * deletion and excludes the record with the provided id)
	 * 
	 * @param id       of user to exclude
	 * @param username
	 * @return true or false
	 */
	boolean existsByIdNotAndUsernameAndIsDeleteFalse(Long id, String username);
}
