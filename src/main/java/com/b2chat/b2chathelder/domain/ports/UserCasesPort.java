package com.b2chat.b2chathelder.domain.ports;

import com.b2chat.b2chathelder.domain.models.User;

public interface UserCasesPort {
	User create(UserCreateInput userCreateInput);

	User readById(Long id);

	User update(Long id, UserUpdateInput userUpdateInput);

	void delete(Long id);
}
