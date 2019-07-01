package loc.chripoli.rest_controlled_spark_batch_demo;

import loc.chripoli.rest_controlled_spark_batch_demo.model.User;
import loc.chripoli.rest_controlled_spark_batch_demo.util.StaticUserRepository;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.codehaus.jackson.map.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Driver program, responsible for getting a static user list, computing their fullnames and writing the output as json to a user / date specific file.
 *
 * @author chrioli
 */
@ApplicationScoped
public class DriverProgram {

    private static final String OUTPUT_FOLDER_ENV_KEY = "OUTPUT_FOLDER";

    private static final String fileOutputFormat = "%s_%tT_result.json";

    @Inject
    private SparkConf sparkConf;

    /**
     * Computes and writes the output file. Since only one SparkContext per application can exist, it is synchronized, thus only one thread can execute in parallel.
     *
     * @param triggerUser
     *      User who triggered the compuation.
     */
    public synchronized  void computeAndWriteResultFile(String triggerUser) {

        startSparkComputation(triggerUser);

    }

    private void startSparkComputation(String triggerUser) {

        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);

        JavaRDD<User> userJavaRDD = javaSparkContext.parallelize(StaticUserRepository.getUserList());

        JavaRDD<User> mappedFullNameUsers = userJavaRDD.map(x -> {
                x.setFullName(x.getFirstName() + " " + x.getLastName());
                return x;
                }
        );

        List<User> result = mappedFullNameUsers.collect();

        javaSparkContext.close();

        writeResultsToFile(result, triggerUser);

    }

    private void writeResultsToFile(final List<User> results, final String triggerUser) {

        final String outputFolder = System.getenv(OUTPUT_FOLDER_ENV_KEY);

        final ObjectMapper objectMapper = new ObjectMapper();


        try {
            objectMapper.writeValue(new File(String.format("%s/%s", outputFolder, String.format(fileOutputFormat, triggerUser, new Date()))), results);
        } catch (IOException e) {
            // currently do nothing for demo purposes only
        }

    }

}
