package com.b2chat.b2chathelder.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private long id;
	private String username;
	private String email;
	private boolean isActive;
}
