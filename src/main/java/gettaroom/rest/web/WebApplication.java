package gettaroom.rest.web;

import gettaroom.rest.api.resources.RoomsResource;
import gettaroom.rest.api.resources.RoomsResourceImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by golanet on 25/01/2017.
 */
public class WebApplication extends Application<ApplicationConfiguration> {

    public static void main(String[] args) throws Exception {
        new WebApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {

//            bootstrap.addBundle(new AssetsBundle("/static", "/", "index.html"));
//            bootstrap.addBundle(new AssetsBundle("/static/styles", "/styles", null, "styles"));
//            bootstrap.addBundle(new AssetsBundle("/static/js", "/js", null, "js"));
//            bootstrap.addBundle(new AssetsBundle("/static/images", "/images", null, "images"));

        bootstrap.addBundle(new SwaggerBundle<ApplicationConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ApplicationConfiguration applicationConfiguration) {
                return applicationConfiguration.swaggerBundleConfiguration;
            }
        });
    }

    public void run(ApplicationConfiguration conf, Environment environment) throws Exception {
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Register resources
        RoomsResource roomsResource = new RoomsResourceImpl();
        environment.jersey().register(roomsResource);
//        environment.jersey().register(actionsResource);

    }
}
