package com.usersapp.modules.user.controller;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.usersapp.modules.user.dto.UserCreateDTO;
import com.usersapp.modules.user.dto.UserEmailProviderDTO;
import com.usersapp.modules.user.dto.UserResponseDTO;
import com.usersapp.modules.user.service.UserService;

@Path("/users")
@Produces("application/json")
@Consumes("application/json")
public class UserController {

	private @Inject UserService userService;
	
	@GET
    public List<UserResponseDTO> findAll(@DefaultValue("") @QueryParam("name") String name) {
        return userService.findAll(name);
    }
	
	@GET
	@Path("/{id}")
	public UserResponseDTO findById(@PathParam("id") Integer id) {
		return userService.findByIdResponse(id);
	}
	
	@GET
	@Path("/birthdays")
	public List<UserResponseDTO> findAllBirthdaysOfMonth() {
		return userService.findAllBirthdaysOfMonth(LocalDate.now().getMonthValue());
	}
	
	@GET
	@Path("/birthdays/{month}")
	public List<UserResponseDTO> findAllBirthdaysOfMonth(@PathParam("month") Integer month) {
		return userService.findAllBirthdaysOfMonth(month);
	}
	
	@GET
	@Path("/providers")
	public List<UserEmailProviderDTO> findAllEmailProviders() {
		return userService.findAllEmailProviders();
	}
	
	@POST
	public UserResponseDTO save(@Valid UserCreateDTO userDTO) {
		return userService.save(userDTO);
	}
	
	@PUT
	@Path("/{id}")
	public UserResponseDTO update(@PathParam("id") Integer id, @Valid UserCreateDTO userDTO) {
		return userService.update(userDTO, id);
	}
	
	@DELETE
	@Path("/{id}")
	public UserResponseDTO delete(@PathParam("id") Integer id) {
		return userService.delete(id);
	}
}
