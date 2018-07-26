FROM openjdk:8u151-jre-alpine3.7

COPY demo*.jar demo.jar

EXPOSE 8080 8088

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,address=8088,server=y,suspend=n",  "-jar", "/documentprocessor.jar"]