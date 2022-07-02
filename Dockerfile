FROM openjdk:18.0.1.1-oraclelinux7
EXPOSE 8080
ADD target/Artwork-Microservice-0.0.1.jar Artwork-Microservice-0.0.1.jar
ENTRYPOINT ["java","-jar","Report-Microservice-0.0.1.jar"]