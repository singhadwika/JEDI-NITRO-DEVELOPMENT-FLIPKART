package com.flipfit;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import io.dropwizard.db.DataSourceFactory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * The Class FlipFitConfiguration.
 * Dropwizard configuration class for the FlipFit application.
 *
 * @author FlipFit Development Team
 * @version 1.0
 */
public class FlipFitConfiguration extends Configuration {

    /** The database configuration. */
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    /** The application name. */
    @NotEmpty
    @JsonProperty
    private String appName;

    /** The application version. */
    @NotEmpty
    @JsonProperty
    private String version;

    /**
     * Gets the database configuration.
     *
     * @return the database factory
     */
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    /**
     * Sets the database configuration.
     *
     * @param database the database factory to set
     */
    public void setDataSourceFactory(DataSourceFactory database) {
        this.database = database;
    }

    /**
     * Gets the application name.
     *
     * @return the app name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Sets the application name.
     *
     * @param appName the app name to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Gets the application version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the application version.
     *
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
