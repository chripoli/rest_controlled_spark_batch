package loc.chripoli.rest_controlled_spark_batch_demo_war.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Basic Jersey 2.x configuration file.
 *
 * @author chripoli
 */
@ApplicationPath("/rest")
public class JerseyApplication extends ResourceConfig {

    public JerseyApplication() {
        packages("loc.chripoli.rest_controlled_spark_batch_demo_war.resources");
    }
}
