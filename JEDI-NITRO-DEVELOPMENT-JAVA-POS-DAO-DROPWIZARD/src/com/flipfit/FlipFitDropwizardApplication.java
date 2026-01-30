package com.flipfit;

import com.flipfit.client.*;
import com.flipfit.exception.*;
import com.flipfit.health.DatabaseHealthCheck;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.jdbi3.JdbiFactory;
import org.jdbi.v3.core.Jdbi;

/**
 * The Class FlipFitDropwizardApplication.
 * Main entry point for the FlipFit Dropwizard application.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class FlipFitDropwizardApplication extends Application<FlipFitConfiguration> {

    /**
     * The main method.
     * Launches the Dropwizard application.
     *
     * @param args the command line arguments
     * @throws Exception if application fails to start
     */
    public static void main(String[] args) throws Exception {
        new FlipFitDropwizardApplication().run(args);
    }

    /**
     * Gets the application name.
     *
     * @return the application name
     */
    @Override
    public String getName() {
        return "FlipFit";
    }

    /**
     * Initializes the application bootstrap.
     *
     * @param bootstrap the bootstrap configuration
     */
    @Override
    public void initialize(Bootstrap<FlipFitConfiguration> bootstrap) {
        // Application initialization
    }

    /**
     * Runs the application and registers all resources.
     *
     * @param configuration the application configuration
     * @param environment the application environment
     */
    @Override
    public void run(FlipFitConfiguration configuration, Environment environment) {
        // Create JDBI instance
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");

        // Register REST Controllers
        environment.jersey().register(new UserController());
        environment.jersey().register(new AdminController());
        environment.jersey().register(new GymCustomerController());
        environment.jersey().register(new GymOwnerController());
        environment.jersey().register(new GymCenterController());
        environment.jersey().register(new BookingController());
        environment.jersey().register(new SlotController());

        // Register Exception Mappers
        environment.jersey().register(new GenericExceptionMapper());
        environment.jersey().register(new InvalidCredentialsExceptionMapper());
        environment.jersey().register(new UserNotFoundExceptionMapper());
        environment.jersey().register(new SlotNotAvailableExceptionMapper());
        environment.jersey().register(new GymCenterNotFoundExceptionMapper());

        // Register Health Checks
        environment.healthChecks().register("database", new DatabaseHealthCheck(jdbi));

        // Enable CORS
        environment.jersey().register(CorsFilter.class);

        System.out.println("=================================================");
        System.out.println("  FlipFit Dropwizard Application Started!");
        System.out.println("  Application: http://localhost:8080");
        System.out.println("  Admin: http://localhost:8081");
        System.out.println("=================================================");
    }
}
