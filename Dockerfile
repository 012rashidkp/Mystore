FROM openjdk:11.0.16
EXPOSE 8080
RUN mkdir /app
COPY ./build/libs/*-all.jar /app/mystore.net.mystore-all.jar
ENTRYPOINT ["java", "-jar", "/app/mystore.net.mystore-all.jar"]
