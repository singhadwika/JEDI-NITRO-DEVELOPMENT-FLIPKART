package com.flipfit.exception;

import com.flipfit.dto.ApiResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * The Class SlotNotAvailableExceptionMapper.
 * Exception mapper for SlotNotAvailableException.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Provider
public class SlotNotAvailableExceptionMapper implements ExceptionMapper<SlotNotAvailableException> {

    /**
     * Converts a SlotNotAvailableException to a JAX-RS response.
     *
     * @param exception the exception
     * @return the response
     */
    @Override
    public Response toResponse(SlotNotAvailableException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ApiResponse<>(false, exception.getMessage(), null))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
