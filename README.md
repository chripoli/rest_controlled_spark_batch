# Readme

In order to run this application the following steps are necessary:

* Build the project on root level with 

``
mvn package
``

* Use docker compose to build the Spark and application image and run it:

``
docker-compose up
``

When the application is up, simply open your browser and call: 

http://localhost:8081/war/rest/spark/trigger?triggerUser=$TRIGGER_USER

This should trigger a calculation which will result on a result file being written
into "/apps/output" within the docker container.