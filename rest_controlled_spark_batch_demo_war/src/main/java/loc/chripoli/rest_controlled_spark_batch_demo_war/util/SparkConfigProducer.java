package loc.chripoli.rest_controlled_spark_batch_demo_war.util;

import org.apache.spark.SparkConf;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Provides the SparkConfig.
 * Configuration is mainly based on environment variables injected into the Docker container.
 *
 * @author chripoli
 */
@ApplicationScoped
public class SparkConfigProducer {

    private static final String SPARK_MASTER_ENV_KEY = "SPARK_MASTER";
    private static final String JARS_ENV_KEY = "JARS";
    private static final String APP_NAME = "Spark Demo with REST triggered batch computation";

    @Produces
    @ApplicationScoped
    public SparkConf produceSparkContext() {

        final String master = System.getenv(SPARK_MASTER_ENV_KEY);
        final String[] jars = System.getenv(JARS_ENV_KEY).split(";");

        final SparkConf config = new SparkConf();
        config.setMaster(master);
        config.setJars(jars);
        config.setAppName(APP_NAME);
        config.set("spark.dynamicAllocation.enabled", "false");
        config.set("spark.rdd.compress", "true");
        config.set("spark.executor.memory", "512M");

        return config;

    }
}
