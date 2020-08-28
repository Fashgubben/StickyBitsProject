FROM openjdk
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp/src
RUN javac Armada.java
CMD ["java", "Armada"]
