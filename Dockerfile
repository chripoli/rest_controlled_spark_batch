FROM payara/micro:5.192

ENV SPARK_MASTER="spark://master:7077"
ENV JARS="/apps/driver.jar"
ENV OUTPUT_FOLDER="/apps/output"

USER root

RUN mkdir -p /apps/output

COPY rest_controlled_spark_batch_demo_war/target/war.war $DEPLOY_DIR
COPY rest_controlled_spark_batch_demo_driver/target/driver.jar /apps/

