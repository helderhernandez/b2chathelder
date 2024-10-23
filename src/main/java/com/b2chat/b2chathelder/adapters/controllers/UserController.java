package com.b2chat.b2chathelder.adapters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.b2chat.b2chathelder.domain.dto.UserCreateInput;
import com.b2chat.b2chathelder.domain.dto.UserUpdateInput;
import com.b2chat.b2chathelder.domain.models.User;
import com.b2chat.b2chathelder.domain.services.GithubService;
import com.b2chat.b2chathelder.domain.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(UserController.URI_USERS)
public class UserController {
	public static final String URI_USERS = "/users";
	public static final String URI_ID = "/{id}";
	public static final String URI_PROFILE = "/profile";

	@Autowired
	private UserService userService;

	@Autowired
	private GithubService gitHubService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a new user")
	public User create(@RequestBody UserCreateInput userCreateInput) {
		return userService.create(userCreateInput);
	}

	@GetMapping(URI_ID)
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Retrieving user by id")
	public User read(@PathVariable Long id) {
		return userService.read(id);
	}

	@PutMapping(URI_ID)
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Updating user information")
	public User update(@PathVariable Long id, @RequestBody UserUpdateInput userUpdateInput) {
		return userService.update(id, userUpdateInput);
	}

	@DeleteMapping(URI_ID)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Deleting a user")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@GetMapping(URI_PROFILE)
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Retrieving github user profile")
	public ResponseEntity<String> profile(OAuth2AuthenticationToken authentication) {
		return gitHubService.profile(authentication);
	}
}
