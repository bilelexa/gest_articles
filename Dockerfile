# base image
FROM openjdk:8-jre-alpine
# set working directory
RUN mkdir /app
WORKDIR /app
# add jar
ADD target/*.jar app.jar
# expose port
EXPOSE 3100
# start app
ENTRYPOINT java -jar app.jar