package loc.chripoli.rest_controlled_spark_batch_demo_war.resources;

import loc.chripoli.rest_controlled_spark_batch_demo.DriverProgram;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Resource responsible for triggering the Spark computation. Uses an execution service for asynchronously work on the Spark computation.
 *
 * @author chripoli
 */
@Path("/spark")
@RequestScoped
public class SparkTriggerResource {

    @Inject
    private DriverProgram driverProgram;

    @Resource(name = "concurrent/myExecutor")
    private ManagedExecutorService executorService;

    /**
     * Triggers the Spark computation represented by the DriverProgram.
     *
     * @param triggerUser
     *      User who triggered the compuation.
     * @return
     *      Basic info message
     */
    @Path("/trigger")
    @GET
    @Produces("text/plain")
    public String triggerSparkBatch(@QueryParam("triggerUser") String triggerUser) {

        executorService.submit(() -> {

            driverProgram.computeAndWriteResultFile(triggerUser);

        });

        return String.format("Batch run has been triggered with user %s", triggerUser);

    }


}
