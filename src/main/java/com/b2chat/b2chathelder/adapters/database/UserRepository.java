package com.b2chat.b2chathelder.adapters.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByUsernameAndIsDeleteFalse(String username);

	boolean existsByEmailAndIsDeleteFalse(String email);

	Optional<UserEntity> findByIdAndIsDeleteFalse(Long id);

	boolean existsByIdAndIsDeleteFalse(Long id);

	boolean existsByIdNotAndUsernameAndIsDeleteFalse(Long id, String username);
}
