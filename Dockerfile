FROM openjdk:22-oracle

WORKDIR /server

COPY data/ .
ADD data/plugin ./plugins
ADD data/server.properties .

EXPOSE 25565/udp
EXPOSE 25565/tcp

EXPOSE 25575/tcp

ENTRYPOINT ["java", "-Xms1024M", "-Xmx2048M", "-Dfile.encoding=UTF-8", "-Djava.net.preferIPv4Stack=true" ,"-jar", "spigot.jar", "nogui"]