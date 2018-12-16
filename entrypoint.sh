#!/bin/bash
#set -e
SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE:-dev}
# Start notepad application with specific JVM_ARGS and SPRING_PROFILE
java -jar -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} ${TEST_HOME}/personality-test-1.0.0.jar