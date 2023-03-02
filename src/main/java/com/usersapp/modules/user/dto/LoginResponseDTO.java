package com.usersapp.modules.user.dto;

import com.usersapp.modules.user.model.User;

public class LoginResponseDTO {

	private Integer id;
	private String login;
	private String name;
	private Boolean isLogged;
	
	public LoginResponseDTO() {
	}

	public LoginResponseDTO(Integer id, String login, String name, Boolean isLogged) {
		this.id = id;
		this.login = login;
		this.name = name;
		this.isLogged = isLogged;
	}
	
	public static LoginResponseDTO of(User user) {
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setId(user.getId());
		loginResponseDTO.setLogin(user.getLogin());
		loginResponseDTO.setName(user.getName());
		loginResponseDTO.setIsLogged(true);
		return loginResponseDTO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(Boolean isLogged) {
		this.isLogged = isLogged;
	}
}
