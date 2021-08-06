FROM openjdk:8
EXPOSE 9000
ADD target/feast-freedom.jar feast-freedom.jar 
ENTRYPOINT ["java","-jar","feast-freedom.jar"]