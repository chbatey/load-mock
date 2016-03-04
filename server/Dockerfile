FROM phusion/baseimage:0.9.17

ENV JAVA_HOME /usr/jdk1.8.0_66

RUN curl \
  --silent \
  --location \
  --retry 3 \
  --header "Cookie: oraclelicense=accept-securebackup-cookie;" \
  "http://download.oracle.com/otn-pub/java/jdk/8u66-b17/server-jre-8u66-linux-x64.tar.gz" \
    | gunzip \
    | tar x -C /usr/ \
    && ln -s $JAVA_HOME /usr/java \
    && rm -rf $JAVA_HOME/man

ENV PATH ${PATH}:${JAVA_HOME}/bin

ADD ./scripts/start_java.sh /usr/local/bin/
ADD ./target/scala-2.11/server-assembly-0.1-SNAPSHOT.jar /data/load-mock.jar

EXPOSE 8080
CMD start_java.sh -c "-jar /data/load-mock.jar"

