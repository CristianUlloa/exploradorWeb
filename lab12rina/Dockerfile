FROM openjdk:22-ea-2-jdk-windowsservercore-ltsc2022
COPY . /usr/src/lab12rina
WORKDIR /usr/src/lab12rina
RUN ./create_classes.bat.txt
CMD ["java", "-cp", "bin;libs/http-20070405.jar;libs/gson-2.2.4.jar", "com.cristhian.lab12rina.MainClass"]
