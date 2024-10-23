package com.b2chat.b2chathelder.adapters.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.b2chat.b2chathelder.ControllerTestConfig;
import com.b2chat.b2chathelder.domain.dto.UserCreateInput;
import com.b2chat.b2chathelder.domain.dto.UserUpdateInput;
import com.b2chat.b2chathelder.domain.models.User;

import reactor.core.publisher.Mono;

@ControllerTestConfig
public class UserControllerTest {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void create_ok() {
		// @formatter:off
		UserCreateInput userCreateInput = UserCreateInput
				.builder()
				.username("james.10RM")
				.password("1234.ABcd")
				.email("jame@mail.com")
				.build();
		
		webTestClient
			.post()
			.uri(UserController.URI_USERS)
			.body(Mono.just(userCreateInput), UserCreateInput.class)
			.exchange()
			.expectStatus().isCreated()
			.expectBody(User.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertNotNull(result.getId());
					assertEquals(result.getUsername(), userCreateInput.getUsername());
					assertEquals(result.getEmail(), userCreateInput.getEmail());
					assertTrue(result.isActive());
				});
		//@formatter:on	
	}

	@Test
	public void create_badRequest_invalidFormat() {
		// @formatter:off
		UserCreateInput userCreateInput = UserCreateInput
				.builder()
				.username("j@mes")
				.password("1234abcd")
				.email("jamesmailcom")
				.build();
		
		final int TOTAL_ERROS = 3;
		
		webTestClient
			.post()
			.uri(UserController.URI_USERS)
			.body(Mono.just(userCreateInput), UserCreateInput.class)
			.exchange()
			.expectStatus().isBadRequest()
			.expectBody(List.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertEquals(result.size(), TOTAL_ERROS);
				});
		//@formatter:on	
	}

	@Test
	public void create_conflict_usernameAlreadyExists() {
		// @formatter:off
		UserCreateInput userCreateInput = UserCreateInput
				.builder()
				.username("juanito.johns")
				.password("1234.ABcd")
				.email("jame@mail.com")
				.build();
		
		webTestClient
			.post()
			.uri(UserController.URI_USERS)
			.body(Mono.just(userCreateInput), UserCreateInput.class)
			.exchange()
			.expectStatus().is4xxClientError()
			.expectBody(String.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertEquals(result, "Username juanito.johns already exists");
				});
		//@formatter:on	
	}

	@Test
	public void read_ok() {
		Long USER_ID = 1L;

		// @formatter:off
		webTestClient
			.get()
			.uri(uriBuilder -> {
				return uriBuilder
					.path(UserController.URI_USERS + UserController.URI_ID)
					.build(USER_ID);
			})		
			.exchange()
			.expectStatus().isOk()
			.expectBody(User.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertEquals(result.getId(), USER_ID);
				});
		// @formatter:on
	}

	@Test
	public void read_notFoundException() {
		Long USER_ID = 2L;

		// @formatter:off
		webTestClient
			.get()
			.uri(uriBuilder -> {
				return uriBuilder
					.path(UserController.URI_USERS + UserController.URI_ID)
					.build(USER_ID);
			})		
			.exchange()
			.expectStatus().isNotFound()
			.expectBody(String.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertEquals(result, "Not found user with id 2");
				});
		// @formatter:on
	}

	@Test
	public void update_ok() {
		// @formatter:off
		Long USER_ID = 3L;
		
		UserUpdateInput userUpdateInput = UserUpdateInput
				.builder()
				.username("michael.5RM")
				.password("1234.ABcd")
				.isActive(true)
				.build();
		
		webTestClient
			.put()
			.uri(uriBuilder -> {
				return uriBuilder
					.path(UserController.URI_USERS + UserController.URI_ID)
					.build(USER_ID);
			})	
			.body(Mono.just(userUpdateInput), UserUpdateInput.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody(User.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertEquals(result.getId(), USER_ID);
					assertEquals(result.getUsername(), userUpdateInput.getUsername());
					assertTrue(result.isActive());
				});
		//@formatter:on	
	}

	@Test
	public void update_badRequest_invalidFormat() {
		// @formatter:off
		Long USER_ID = 3L;
		
		UserUpdateInput userUpdateInput = UserUpdateInput
				.builder()
				.username("j@mes")
				.password("1234abcd")
				.isActive(null)
				.build();
		
		final int TOTAL_ERROS = 3;
		
		webTestClient
			.put()
			.uri(uriBuilder -> {
				return uriBuilder
					.path(UserController.URI_USERS + UserController.URI_ID)
					.build(USER_ID);
			})	
			.body(Mono.just(userUpdateInput), UserUpdateInput.class)
			.exchange()
			.expectStatus().isBadRequest()
			.expectBody(List.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertEquals(result.size(), TOTAL_ERROS);
				});
		//@formatter:on	
	}

	@Test
	public void update_conflict_usernameAlreadyExists() {
		// @formatter:off
		Long USER_ID = 3L;
		
		UserUpdateInput userUpdateInput = UserUpdateInput
				.builder()
				.username("juanito.johns")
				.password("1234.ABcd")
				.isActive(true)
				.build();
		
		webTestClient
			.put()
			.uri(uriBuilder -> {
				return uriBuilder
					.path(UserController.URI_USERS + UserController.URI_ID)
					.build(USER_ID);
			})	
			.body(Mono.just(userUpdateInput), UserUpdateInput.class)
			.exchange()
			.expectStatus().is4xxClientError()
			.expectBody(String.class)
				.value(Assertions::assertNotNull)
				.value(result -> {
					assertEquals(result, "Username juanito.johns is already used by another registration");
				});
		//@formatter:on	
	}

	@Test
	public void delete_ok() {
		// @formatter:off
		Long USER_ID = 4L;
		
		webTestClient
			.delete()
			.uri(uriBuilder -> {
				return uriBuilder
					.path(UserController.URI_USERS + UserController.URI_ID)
					.build(USER_ID);
			})	
			.exchange()
			.expectStatus().isNoContent();
		//@formatter:on	
	}

}
