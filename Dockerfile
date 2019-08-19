FROM maven:3-jdk-11 as builder

WORKDIR /workspace

COPY . ./
RUN mvn --batch-mode \
        --errors \
        --define maven.test.skip=true \
        --define java.awt.headless=true \
        --activate-profiles production \
        clean package



FROM adoptopenjdk/openjdk11:jre as runner

LABEL \
  org.label-schema.schema-version="1.0" \
  org.label-schema.name="PlantUML Server" \
  org.label-schema.description="A web application to generate UML diagrams on-the-fly."

RUN apt-get update \
 && apt-get install -y --no-install-recommends \
            graphviz \
            fonts-wqy-zenhei \
 && rm -rf /var/lib/apt/lists/*

COPY --from=builder /workspace/target/plantuml.jar /plantuml.jar

ENV JAVA_OPTS=""

EXPOSE 8080/tcp

ENTRYPOINT java $JAVA_OPTS \
    -Xtune:virtualized \
    -Djava.security.egd=file:/dev/./urandom \
    -noverify \
    -XX:TieredStopAtLevel=1 \
    -jar /plantuml.jar
