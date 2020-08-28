FROM openjdk:11

COPY . /usr/src/myapp/
WORKDIR /usr/src/myapp/src/

USER root
# RUN apt-get install junit4 -y

RUN javac Armada.java
CMD ["java", "-classpath", "../lib/mysql-connector-java-8.0.18.jar:.", "Armada"]
