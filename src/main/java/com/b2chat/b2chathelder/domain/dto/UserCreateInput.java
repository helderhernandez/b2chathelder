package com.b2chat.b2chathelder.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateInput {
	@NotBlank
	@Size(min = 4, max = 25)
	@Pattern(regexp = "^[A-Za-z0-9_.-]{4,25}$", message = "Formato invalido")
	private String username;

	@NotBlank
	@Size(min = 8, max = 16)
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[_*#$.,%+=@-])[A-Za-z0-9_*#$.,%+=@-]{8,16}$", message = "Formato invalido")
	private String password;

	@NotBlank
	@Size(min = 8, max = 250)
	@Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email inválido")
	private String email;
}
