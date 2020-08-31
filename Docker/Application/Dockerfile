FROM openjdk:11

COPY . /usr/src/myapp/
WORKDIR /usr/src/myapp/src/
USER root

run javac Armada.java

CMD ["java", "-classpath", "../lib/mysql-connector-java-8.0.18.jar:.", "Armada"]
 
