FROM hub.c.163.com/springcloud/java8:latest

ADD target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","/app.jar"]