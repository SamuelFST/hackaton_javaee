package com.usersapp.modules.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.usersapp.modules.user.dto.UserCreateDTO;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "name", nullable = false)
	@NotEmpty(message = "name field is required")
	@Size(max = 50, message = "the name field have a maximum limit of 50 characters")
	private String name;

	@Column(name = "login", nullable = false, unique = true)
	@Size(min = 5, max = 20, message = "The login field must have between 5 and 20 characters")
	private String login;

	@Column(name = "email", nullable = false, unique = true)
	@Email
	@Size(min = 10, message = "The email field must have at least 10 characters")
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "date_birth")
	private LocalDate dateBirth;

	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		password = Base64.getEncoder().encodeToString(password.getBytes());
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
		password = Base64.getEncoder().encodeToString(password.getBytes());
	}

	public User() {
	}

	public User(Integer id, String name, String login, String email, String password, LocalDate dateBirth,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.email = email;
		this.password = password;
		this.dateBirth = dateBirth;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static User of(UserCreateDTO userDTO) {
		User user = new User();
		user.setName(userDTO.getName());
		user.setLogin(userDTO.getLogin());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setDateBirth(userDTO.getDateBirth());
		return user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
