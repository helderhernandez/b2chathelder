package com.b2chat.b2chathelder.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Pattern(regexp = "^[A-Za-z0-9_.-]{4,25}$", message = "Invalid format")
	@Schema(example = "elena.diaz1994",
		//@formatter:off
		description = "Valid format:"
			+ "\n - Contain between 4 and 25 characters."
			+ "\n - May contain upper and lower case letters.."
			+ "\n - May contain numeric characters."
			+ "\n - May contain the special characters: . - _")
		//@formatter:on
	private String username;

	@NotBlank
	@Size(min = 8, max = 16)
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[_*#$.,%+=@-])[A-Za-z0-9_*#$.,%+=@-]{8,16}$", message = "Invalid format")
	@Schema(example = "1234.ABcd", 
		//@formatter:off
		description = "Valid format::"
			+ "\n - Contain between 8 and 16 characters."
			+ "\n - Contain at least one uppercase letter"
			+ "\n - Contain at least one lowercase letter"
			+ "\n - Contain at least one numeric character."
			+ "\n - Contain at least one of the following special characters: * # $ . , % + - _ = @")
		//@formatter:on
	private String password;

	@NotBlank
	@Size(min = 8, max = 250)
	@Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid format")
	@Schema(example = "elena@gmail.com")
	private String email;
}
