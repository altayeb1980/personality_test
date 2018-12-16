# Personality Test Spring Boot App
This application is used for connecting to MySQL Database and presents a bunch of questions in the UI. It exposes the actuator endpoints as well as /questions endpoint, which list all the questions separated by category and create user answers when it get POST request.

The application on startup parse the json file and save data according to each entity in the database.

The tables represnted in this application are: Question, QuestionCategory, QuestionType, User.

No need to create schema or prepare database for this application Docker container take care for prepare MYSQL Database and create schema.

The application docker or standalone link to the mysql container, and persist the data to the databse on the startup.

# Usage
The Personality test applications stores the questions in a MySQL instance, so it expects the MySQL database to be up and running. The bellow command starts a MySQL container with a newly created database personality_test_db in it. It also sets up the mysql root password as root.

docker run -d -p 2012:3306 --name mysql-docker-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=personality_test_db -e MYSQL_USER=app_user -e MYSQL_PASSWORD=test123  mysql:latest





# How the application UI work
Once the user enter to the home page, from the nav bar in the left corner of the application, the user able to click over questions menu or click directly in the question from the home page.

The application list all the questions by category, so the user should answer all the questions, if one question did not answered validation will raise to let the user answers all the questions, if the question contains a condition and the exact equals answer meet the answer, the new child question will be open below the main question.

Once the user answers all the questions and click next, dialog will open to let the user enter valid email.



The application jar build automatically by Travis CI, latest build# 31, please check this url for latest build information https://travis-ci.org/altayeb1980/personality_test/builds/468521568

# Instructions for setting the site up in a local sandbox
The application can be run locally in two ways Docker or standalone, please note i am assuming your sandbox is windows OS.

# Instructions for setting the site up by Docker
Make sure the docker container install in your windows machine, I try this docker image in docker window
Type docker ps to make sure the container up and running

Create a folder in your machine with name sparknetworks and let's assume this folder located in c:\
C:\mkdir sparknetworks
cd sparknetworks
C:>sparknetworks>

Clone the project by type git clone https://github.com/altayeb1980/HotelDeals.git
After the clone finish successfully new folder should be created with name personality_test, navigate inside this folder by type
cd personality_test, so the current working directory should be like this
C:>sparknetworks>personality_test>

Type dir you should see list of files and folders listed and the Dockerfile one of them
Type this command:

docker build -t personality-test .

The image should be created, it will take a time during installation of the OpenJDK, at the end of creation for the image you should get this message "Successfully built" which indicate the image build successfully and ready to work on it.
To list the images please type 

docker images

You should see personality-test in the list of images.

Type docker run -p 9090:9090 -t hotel-deal
Tomcat should start running with 9090 port.
To check if the application runs correctly please type: curl http://localhost:9090/actuator/health or by browser just put the URL, you should receive message {"status":"UP"}, means the application is up.
To start working in the application type http://localhost:9090 in your browser
Instructions for setting the site up by stnadalone
Create a folder in your machine with name expedia and let's assume this folder located in c:\
C:\mkdir expedia
cd expedia
C:>expedia >
Clone the project by type git clone https://github.com/altayeb1980/HotelDeals.git
After the clone finish successfully new folder should be created with name HotelDeals, navigate inside this folder by type
cd HotelDeals, so the current work directory should be like this
C:>expedia>HotelDeals>
Type dir you should see the list of files and folders listed with target folder as well
Type cd target
C:>expedia>HotelDeals>cd target
Type dir
The jar name should be listed inside target folder with name "hotel-deals-1.0.0.jar"
Type java -jar hotel-deals-1.0.0
Tomcat should start running with 9090 port.
To check if the application runs correctly please type: curl http://localhost:9090/actuator/health or by browser just put the URL, you should receive message {"status":"UP"}, means the application is up.
To enter to the application type http://localhost:9090 in your browse



