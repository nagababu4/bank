FROM eclipse-temurin:24-jre
WORKDIR /usr/src
ENV MYSQL_USERNAME=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_URL=jdbc:mysql://localhost:3306/bank?useSSL=false&allowPublicKeyRetrieval=true
ADD ./target/transaction-service-0.0.1-SNAPSHOT.jar  /usr/src/transaction-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/usr/src/transaction-service-0.0.1-SNAPSHOT.jar"]