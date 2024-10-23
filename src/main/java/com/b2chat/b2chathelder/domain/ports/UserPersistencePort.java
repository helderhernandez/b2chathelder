package com.b2chat.b2chathelder.domain.ports;

import com.b2chat.b2chathelder.domain.models.User;

public interface UserPersistencePort {
	User create(String username, String password, String email);

	boolean existsUsername(String username);

	boolean existsEmail(String email);

	User findById(Long id);

	User update(Long id, String username, String password, boolean isActive);

	boolean existsById(Long id);

	boolean usernameExistsWithAnotherId(Long id, String username);
	
	void delete(Long id);
}
