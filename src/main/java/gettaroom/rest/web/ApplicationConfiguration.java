package gettaroom.rest.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Created by golanet on 25/01/2017.
 */
class ApplicationConfiguration extends Configuration {


    public ApplicationConfiguration() {}

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

//    @JsonProperty("gettaroom")
//    public GettaRoomConfiguration gettaRoomConfiguration;
}