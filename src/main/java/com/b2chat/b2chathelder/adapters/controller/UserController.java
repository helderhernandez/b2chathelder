package com.b2chat.b2chathelder.adapters.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2chat.b2chathelder.domain.dto.UserCreateInput;
import com.b2chat.b2chathelder.domain.dto.UserUpdateInput;
import com.b2chat.b2chathelder.domain.models.User;
import com.b2chat.b2chathelder.domain.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public User create(@RequestBody UserCreateInput userCreateInput) {
		return userService.create(userCreateInput);
	}

	@GetMapping("/{id}")
	public User read(@PathVariable Long id) {
		return userService.read(id);
	}

	@PutMapping("/{id}")
	public User update(@PathVariable Long id, @RequestBody UserUpdateInput userUpdateInput) {
		return userService.update(id, userUpdateInput);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
}
