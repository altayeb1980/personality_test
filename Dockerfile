# Set the base image to java8
FROM openjdk:8-alpine
# File Author / Maintainer
MAINTAINER Altayeb Saadeh
# Define default environment variables
ENV TEST_HOME=/opt/test
ENV TEST_BINARIES=/opt/test/bin
# Create directory
RUN mkdir -p $TEST_BINARIES
# Set default directory
WORKDIR $TEST_HOME
# Copy personality_test jar file
COPY target/*.jar $TEST_HOME/personality-test-1.0.0.jar
# Add initialization script
ADD entrypoint.sh $TEST_BINARIES/entrypoint.sh
# Give permissions
RUN chmod 755 $TEST_BINARIES/entrypoint.sh
# Expose default servlet container port
EXPOSE 9090
# Main command
ENTRYPOINT ["/bin/sh", "/opt/test/bin/entrypoint.sh"]