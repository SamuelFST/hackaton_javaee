package com.usersapp.config.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionGlobalHandler implements ExceptionMapper<UserException> {
	
	private static final Response.Status BAD_REQUEST = Response.Status.BAD_REQUEST;
	
	@Override
	public Response toResponse(UserException ex) {
		return Response.status(BAD_REQUEST).entity(new ExceptionDetails(BAD_REQUEST.getStatusCode(), ex.getMessage())).build();
	}
}
