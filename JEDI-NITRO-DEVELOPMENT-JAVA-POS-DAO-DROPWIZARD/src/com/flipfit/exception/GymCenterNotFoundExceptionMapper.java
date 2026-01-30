package com.flipfit.exception;

import com.flipfit.dto.ApiResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The Class GymCenterNotFoundExceptionMapper.
 * Exception mapper for GymCenterNotFoundException.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Provider
public class GymCenterNotFoundExceptionMapper implements ExceptionMapper<GymCenterNotFoundException> {

    /**
     * Converts a GymCenterNotFoundException to a JAX-RS response.
     *
     * @param exception the exception
     * @return the response
     */
    @Override
    public Response toResponse(GymCenterNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ApiResponse<>(false, exception.getMessage(), null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
