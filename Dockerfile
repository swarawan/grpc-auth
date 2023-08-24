FROM gradle:8.2.1-jdk17-jammy as build

WORKDIR /code
COPY --link . .

RUN gradle clean generateProto bootJar

FROM eclipse-temurin:17.0.8_7-jre-jammy

WORKDIR /java

LABEL maintainer="swarawan.rio@gmail.com"
ENV LC_CTYPE en_US.UTF-8
ENV TZ=Asia/Jakarta
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime
RUN echo $TZ > /etc/timezone

RUN apt-get update \
    && apt-get install -y --no-install-recommends wget tzdata fonts-dejavu fonts-liberation fontconfig gettext-base curl jq\
    && apt-get purge -y --auto-remove \
    && rm -rf /var/lib/apt/lists/*

COPY --from=build /code/build/libs/*.jar app.jar
COPY --from=build /code/java-run.sh java-run.sh
RUN chmod a+x java-run.sh

EXPOSE 9091
CMD ["/bin/bash", "java-run.sh"]