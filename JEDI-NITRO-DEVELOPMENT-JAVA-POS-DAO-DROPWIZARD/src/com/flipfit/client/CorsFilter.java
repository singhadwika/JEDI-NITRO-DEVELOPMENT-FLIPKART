package com.flipfit.client;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * The Class CorsFilter.
 * CORS filter to enable cross-origin requests.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {

    /**
     * Adds CORS headers to the response.
     *
     * @param requestContext the request context
     * @param responseContext the response context
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", 
                "origin, content-type, accept, authorization");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", 
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}
