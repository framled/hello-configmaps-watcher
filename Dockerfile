FROM azul/zulu-openjdk:21-latest as build

WORKDIR /app
COPY . .

RUN apt-get update && \
    apt-get install git=1:2.34.1-1ubuntu1.11 -y --no-install-recommends

RUN ./gradlew build

FROM azul/zulu-openjdk:21-jre-headless-latest

ARG GIT_REV=0000
ARG IMAGE_VERSION=0.0.0

ARG APP_UID=1000
ARG APP_USER=hello
ARG APP_GID=1000
ARG APP_GROUP=world

COPY --from=build /app/build/libs/hola-mundoservice-${IMAGE_VERSION}.jar /hola-mundoservice/hola-mundoservice.jar

RUN addgroup --gid ${APP_GID} ${APP_GROUP} && \
    adduser --shell /bin/sh --uid ${APP_UID} --disabled-password --ingroup ${APP_GROUP} ${APP_USER} && \
    chown -R ${APP_USER}:${APP_GROUP} /hola-mundoservice

WORKDIR /hola-mundoservice
USER ${APP_USER}

EXPOSE 8099

CMD java -jar hola-mundoservice.jar --server.servlet.context-path=/
