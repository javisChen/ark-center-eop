FROM openjdk:11-oraclelinux8
WORKDIR /app
COPY ./target/{{artifactId}}.jar /app
EXPOSE 8080
ENV SYS_OPT=""
ENTRYPOINT java $SYS_OPT -jar /app/{{artifactId}}.jar
