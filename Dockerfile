FROM openjdk:11
COPY . /usr/src/todo
WORKDIR /usr/src/todo
RUN ./mvnw install -DskipTests
CMD ["java","-jar","target/rltw-todo.jar"]