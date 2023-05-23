

FROM openjdk:13
COPY .   app
WORKDIR  app

RUN mkdir /app/data
RUN mkdir /app/data/batch

RUN ["javac","Registration.java"]
ENTRYPOINT ["java","Registration"]



