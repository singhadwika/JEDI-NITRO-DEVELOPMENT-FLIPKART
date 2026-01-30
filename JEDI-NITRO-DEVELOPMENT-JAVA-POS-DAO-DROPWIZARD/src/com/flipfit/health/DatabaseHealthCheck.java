package com.flipfit.health;

import com.codahale.metrics.health.HealthCheck;
import org.jdbi.v3.core.Jdbi;

/**
 * The Class DatabaseHealthCheck.
 * Health check for database connectivity.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class DatabaseHealthCheck extends HealthCheck {

    /** The JDBI instance. */
    private final Jdbi jdbi;

    /**
     * Instantiates a new database health check.
     *
     * @param jdbi the JDBI instance
     */
    public DatabaseHealthCheck(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Checks the database connectivity.
     *
     * @return the health check result
     * @throws Exception if health check fails
     */
    @Override
    protected Result check() throws Exception {
        try {
            jdbi.useHandle(handle -> {
                handle.createQuery("SELECT 1").mapTo(Integer.class).one();
            });
            return Result.healthy("Database connection is healthy");
        } catch (Exception e) {
            return Result.unhealthy("Database connection failed: " + e.getMessage());
        }
    }
}
