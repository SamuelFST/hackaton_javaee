package com.usersapp.modules.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.usersapp.config.exception.UserException;
import com.usersapp.modules.user.dao.UserDAO;
import com.usersapp.modules.user.dto.UserCreateDTO;
import com.usersapp.modules.user.dto.UserEmailProviderDTO;
import com.usersapp.modules.user.dto.UserResponseDTO;
import com.usersapp.modules.user.model.User;

@ApplicationScoped
public class UserService {

	private @Inject UserDAO userDAO;

	public List<UserResponseDTO> findAll(String name) {
		List<User> users = new ArrayList<User>();
		
		if (name == "") {
			users.addAll(userDAO.findAll());			
		} else {
			users.addAll(userDAO.findAllByNameInitials(name));
		}

		return users.stream().map(user -> UserResponseDTO.of(user)).collect(Collectors.toList());
	}

	public UserResponseDTO findByIdResponse(Integer id) {
		validateInformedId(id);
		return UserResponseDTO.of(findById(id));
	}

	public List<UserResponseDTO> findAllBirthdaysOfMonth(Integer month) {
		List<User> birthdayUsers = userDAO.findAllBirthdaysOfMonth(month);
		return birthdayUsers.stream().map(user -> UserResponseDTO.of(user)).collect(Collectors.toList());
	}
	
	public List<UserEmailProviderDTO> findAllEmailProviders() {
		return userDAO.findAllEmailProviders().stream().map(provider -> UserEmailProviderDTO.of(provider)).collect(Collectors.toList());
	}
	
	public UserResponseDTO save(UserCreateDTO userDTO) {
		try {
			validateUserData(userDTO);
			validateInformedPassword(userDTO.getPassword());
			User user = userDAO.save(User.of(userDTO));
			return UserResponseDTO.of(user);
		} catch (Exception ex) {
			throw new UserException(ex.getMessage().toString());
		}
	}

	public UserResponseDTO update(UserCreateDTO userDTO, Integer id) {
		validateUserData(userDTO);
		validateInformedId(id);
		User userToBeUpdated = findById(id);

		if (userToBeUpdated.getEmail() == userDTO.getEmail()) {
			userToBeUpdated.setEmail(null);
		}

		User user = User.of(userDTO);
		user.setId(id);

		userDAO.update(user);
		return UserResponseDTO.of(user);
	}

	public UserResponseDTO delete(Integer id) {
		validateInformedId(id);
		User user = findById(id);
		userDAO.delete(user);
		return UserResponseDTO.of(user);
	}

	private void validateUserData(UserCreateDTO user) {
		if (user.getName() == "") {
			throw new UserException("The user name field is required");
		}

		if (user.getLogin() == "") {
			throw new UserException("The login field is required");
		}

		if (user.getEmail() == "") {
			throw new UserException("The email field is required");
		}

		if (user.getPassword() == "") {
			throw new UserException("The password field is required");
		}

		if (user.getDateBirth() == null) {
			throw new UserException("The birth date is required");
		}
	}
	
	private void validateInformedPassword(String password) {
		if (password.length() < 4 || password.length() > 10) {
			throw new UserException("The password must have between 4 and 10 characters");
		}
		
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$";
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(password);
		
		if (!matcher.matches()) {
			throw new UserException("The password must contain one uppercase, lowercase, numeric and special character");
		}
	}

	private void validateInformedId(Integer id) {
		if (id == null) {
			throw new UserException("The user id was not informed");
		}
	}

	public User findById(Integer id) {
		validateInformedId(id);
		try {
			return userDAO.findById(id).get();
		} catch (Exception ex) {
			throw new UserException("No user with id " + id);
		}
	}
}
