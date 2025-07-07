FROM openjdk:21

COPY target/ProductService-0.0.1-SNAPSHOT.jar productservice.jar

# java -jar productservice.jar

ENTRYPOINT ["java", "-jar", "productservice.jar"]