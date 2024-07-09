# Stage 1: Build the application
FROM openjdk:21
ARG JAR_FILE=build/libs/*.jar
ENV PORT 8080
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]