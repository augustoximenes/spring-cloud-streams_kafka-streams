FROM openjdk:18-jdk AS builder
WORKDIR target/dependency
ARG APPJAR=target/*-jar-with-dependencies.jar
COPY ${APPJAR} app.jar
RUN jar -xf ./app.jar

FROM openjdk:18-jdk
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","dev.augustoximenes.kafka.streams.StreamsApplication"]