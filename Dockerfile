FROM openjdk:8

# File Author / Maintainer
MAINTAINER Altayeb Saadeh

VOLUME /tmp
ENV SPRING_BOOT_DOCKER_APP_HOME=/opt/boot-docker
ENV SPRING_BOOT_DOCKER_APP_BINARIES=/opt/boot-docker/bin

# Create directory
RUN mkdir -p $SPRING_BOOT_DOCKER_APP_BINARIES

WORKDIR $SPRING_BOOT_DOCKER_APP_HOME

COPY target/*.jar $SPRING_BOOT_DOCKER_APP_HOME/hotel-deals.jar

# Expose default servlet container port
EXPOSE 9090

# Main command
ENTRYPOINT ["java", "-jar","/opt/boot-docker/hotel-deals.jar"]