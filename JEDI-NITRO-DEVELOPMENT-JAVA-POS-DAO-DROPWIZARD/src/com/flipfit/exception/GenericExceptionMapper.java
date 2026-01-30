package com.flipfit.exception;

import com.flipfit.dto.ApiResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The Class GenericExceptionMapper.
 * Global exception mapper for handling uncaught exceptions.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    /**
     * Converts an exception to a JAX-RS response.
     *
     * @param exception the exception
     * @return the response
     */
    @Override
    public Response toResponse(Exception exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ApiResponse<>(false, "An unexpected error occurred: " + exception.getMessage(), null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
