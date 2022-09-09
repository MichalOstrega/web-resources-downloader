# Web Resources Downloader

**OPIS**
1. Aplikacja udostępnia usługę REST umożliwiającą przekazanie URL strony do pobrania.
2. Kolejkowanie oparte jest na brokerze wiadomości Apache Kafka
3. Aplikacja skonfigurowana jest do uruchomienia za pomocą narzędzia Docker-compose
4. Aplikacja udostępnia wizualizację zasobów - SWAGGER UI 
5. Aplikacja udostępnia Kafka GUI - AKHQ 

**URUCHOMIENIE**
1. Zbudowanie aplikacji za pomocą narzędzia MAVEN 

*mvn package -DskipTests*

2. Zbudowanie obrazu kontenera oraz uruchomienie kontenerów przy pomocy narzędzia Docker-compose

*docker-compose -f .\docker-compose-kafka.yml up*



**SWAGGER-URL**

http://localhost:28085/swagger-ui/index.html

**AKHQ-URL**

http://localhost:28080

**API-DOCS-URL**

http://localhost:28085/v3/api-docs



