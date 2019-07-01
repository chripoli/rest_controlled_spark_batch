package loc.chripoli.rest_controlled_spark_batch_demo_war.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.spark_project.guava.util.concurrent.Service;

import java.util.Set;

/**
 * Dummy service provider. Was necessary to circumwent CDI injection problems.
 *
 * @author chripoli
 */
@ApplicationScoped
public class DummyServiceProducer {

    @Produces
    @ApplicationScoped
    public Set<Service> dummyServices() {
        throw new AssertionError();
    }
}
