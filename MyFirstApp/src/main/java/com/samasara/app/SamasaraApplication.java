package com.samasara.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.samasara.app.application.SamasaraApplicationModule;
import com.samasara.app.configuration.SamasaraConfig;
import com.samasara.app.core.models.dbModels.LoginCredentials;
import com.samasara.app.core.resources.LoginResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by waghmode.tayappa on 04/09/16.
 */
public class SamasaraApplication extends Application<SamasaraConfig> {

    private static final Logger logger = LoggerFactory.getLogger(SamasaraApplication.class);

    public static void main(String [] args) throws Exception{

        try{
            if (args.length == 0){
                args = new String[]{"server", "src/main/resources/configuration/prod.yml"};
            }
        }catch (Exception e){
            logger.info("Error while starting server ", e);
        }
        new SamasaraApplication().run(args);

    }

    @Override
    public void run(SamasaraConfig config, Environment environment) throws Exception {

        Injector injector = createInjector(config,hibernateBundle);
        registerResource(injector, environment);
        addFilters(environment);

    }

    @Override
    public void initialize(Bootstrap<SamasaraConfig> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    private Injector createInjector(SamasaraConfig config, HibernateBundle<SamasaraConfig> hibernateBundle){
        return Guice.createInjector( new SamasaraApplicationModule( config, hibernateBundle.getSessionFactory()) );
    }

    private void registerResource(Injector inj, Environment env){
        env.jersey().register(inj.getInstance(LoginResource.class));
    }

    private void addFilters(Environment env){
        FilterRegistration.Dynamic filter =
                env.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter("allowedOrigins", "*");
        filter.setInitParameter("allowedHeaders",
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,X-MZ-Token");
        filter.setInitParameter("allowedMethods", "GET,PUT,POST,DELETE,OPTIONS,HEAD");
        filter.setInitParameter("preflightMaxAge", "5184000");
        filter.setInitParameter("allowCredentials", "true");

    }
    private final HibernateBundle<SamasaraConfig> hibernateBundle = new HibernateBundle<SamasaraConfig>
            (LoginCredentials.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(SamasaraConfig config) {
            return config.getDb();
        }
    };
}
