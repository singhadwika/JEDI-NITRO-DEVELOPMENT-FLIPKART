package com.flipfit.exception;

import com.flipfit.dto.ApiResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The Class UserNotFoundExceptionMapper.
 * Exception mapper for UserNotFoundException.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

    /**
     * Converts a UserNotFoundException to a JAX-RS response.
     *
     * @param exception the exception
     * @return the response
     */
    @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ApiResponse<>(false, exception.getMessage(), null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
