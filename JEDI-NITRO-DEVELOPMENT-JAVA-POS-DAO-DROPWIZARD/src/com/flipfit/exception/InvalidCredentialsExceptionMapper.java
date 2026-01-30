package com.flipfit.exception;

import com.flipfit.dto.ApiResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The Class InvalidCredentialsExceptionMapper.
 * Exception mapper for InvalidCredentialsException.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Provider
public class InvalidCredentialsExceptionMapper implements ExceptionMapper<InvalidCredentialsException> {

    /**
     * Converts an InvalidCredentialsException to a JAX-RS response.
     *
     * @param exception the exception
     * @return the response
     */
    @Override
    public Response toResponse(InvalidCredentialsException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ApiResponse<>(false, exception.getMessage(), null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
