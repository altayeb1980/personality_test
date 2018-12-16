# Personality Test Spring Boot App
This application is used for connecting to MySQL Database and presents a bunch of questions in the UI. It exposes the actuator endpoints as well as /questions endpoint, which list all the questions separated by category and create user answers when it get POST request.

Latest Travis-CI build for this application can be found in :

https://travis-ci.org/altayeb1980/personality_test/builds/468753273


The application on startup parse the json file and save data according to each entity in the database.

The tables represnted in this application are: Question, QuestionCategory, QuestionType, User.

No need to create schema or prepare database for this application Docker container take care for prepare MYSQL Database and create schema.

The application docker or standalone link to the mysql container, and persist the data to the databse on the startup.

# Usage
The Personality test applications stores the questions in a MySQL instance, so it expects the MySQL database to be up and running. The bellow command starts a MySQL container with a newly created database personality_test_db in it. It also sets up the mysql root password as root.

docker run -d -p 2012:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=personality_test_db -e MYSQL_USER=app_user -e MYSQL_PASSWORD=test123  mysql:latest

Personality Test application can be run in two ways, local sandbox or docker container.

# Instructions for setting the site up in a local sandbox

clone this repository and go into personality_test directory:

$git https://github.com/altayeb1980/personality_test.git $cd personality_test

Once you succesfully cloned the repository, start the application the jar artifact:

$ mvn clean install -Ptest

Wait for the unit and integration tests to run and the artifact to be generated. Eventually it will be stored in the target/ directory.

Now, start the application:

$java -jar "-Dspring.profiles.active=test"  $(ls target/*.jar)

Check that the application is up and running hitting the actuator /health endpoint:
$ curl http://localhost:9090/actuator/health or by browser just put the URL, you should receive message {"status":"UP"}, means the application is up.


# Instructions for setting the site up by Docker

clone this repository and go into personality_test directory:

$git https://github.com/altayeb1980/personality_test.git $cd personality_test

Once you succesfully cloned the repository, start creating docker image:

docker build -t personality-test .

The image should be created, it will take a time during installation of the OpenJDK, at the end of creation for the image you should get this message "Successfully built" which indicate the image build successfully and ready to work on it.

To list the images please type 

docker images

You should see personality-test in the list of images.

now, let's run our docker container with profile prod.

docker run -t --name personality_test_container -e "SPRING_PROFILES_ACTIVE=prod" -e "DATABASE_HOST=mysql-docker-container" -e "DATABASE_PORT=3306" -e "DATABASE_USER=app_user" -e "DATABASE_PASSWORD=test123" -e "DATABASE_SCHEMA=personality_test_db"  --link mysql-docker-container:mysql -p 9090:9090 personality-test


Check that the application is up and running hitting the actuator /health endpoint:

$ curl http://localhost:9090/actuator/health or by browser just put the URL, you should receive message {"status":"UP"}, means the application is up.

# How the application work
To start working in the application type http://localhost:9090 in your browser.

Once the user enter to the home page, from the nav bar in the left corner of the application, the user able to click over questions menu or click directly in the question from the home page.

The application list all the questions by category, so the user should answer all the questions, if one question did not answered validation will raise to let the user answers all the questions, if the question contains a condition and the exact equals answer meet the answer, the new child question will be open below the main question.

Once the user answers all the questions and click next, dialog will open to let the user enter valid email, once the user enter his email notification should appear to notify the user his answers saved suucessfully.

The same user can answers the questions more than one time, the application override the prevoius qnswers for the same user.

