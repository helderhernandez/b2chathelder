package com.b2chat.b2chathelder.adapters.database;

import com.b2chat.b2chathelder.domain.models.User;

public class UserMapper {
	/**
	 * Convert entity to domain
	 * @param userEntity
	 * @return user domain
	 */
	public static User entityToDomain(UserEntity userEntity) {
		//@formatter:off
		User user = User.builder()
				.id(userEntity.getId())
				.username(userEntity.getUsername())
				.email(userEntity.getEmail())
				.isActive(userEntity.isActive())
				.build();
		//@formatter:on

		return user;
	}
}
