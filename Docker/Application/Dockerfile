FROM openjdk:7

COPY /home/tim/Code/Linux2/StickyBitsProject/ /usr/src/my_java_app
WORKDIR /usr/src/myapp

USER root
RUN apt-get install junit4

RUN javac Armada.java
CMD ["java", "Armada"]
