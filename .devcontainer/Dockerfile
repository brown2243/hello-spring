FROM openjdk:17-jdk-slim

# git
RUN apt-get update && apt-get install -y git && apt-get clean

ENV JAVA_HOME=/usr/local/openjdk-17
ENV PATH=$JAVA_HOME/bin:$PATH

WORKDIR /app
COPY . /app
CMD ["java", "-version"]