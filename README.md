# KafkaProducerPoc

**Descripción:**
Este es un proyecto Java que a travez de un producer envia un mensaje a un topico de kafka.

**Tecnologías:**
* Java 17
* Spring Boot 3
* Maven

**Instalación:**
1. Clonar el repositorio: `git clone 
2. Instalar dependencias: `mvn clean install`
3. Ejecutar la aplicación: `java -jar target/KafkaProducerPoc.jar`

#**Uso:**
Antes de  utilizar la aplicación, ejecuta el siguiente comando para levantar Kafka con kafdrop:

Docker-compose up -d

sobre el archivo Docker-compose.yml

debe contener:
services:
zookeeper:
image: confluentinc/cp-zookeeper:latest
networks:
- broker-kafka
environment:
ZOOKEEPER_CLIENT_PORT: 2181
ZOOKEEPER_TICK_TIME: 2000

kafka:
image: confluentinc/cp-kafka:latest
networks:
- broker-kafka
depends_on:
- zookeeper
ports:
- "9092:9092"
environment:
KAFKA_BROKER_ID: 1
KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:29092,PLAINTEXT_HOST://localhost:9092
KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

kafdrop:
image: obsidiandynamics/kafdrop:latest
networks:
- broker-kafka
depends_on:
- kafka
ports:
- "19000:9000"
environment:
KAFKA_BROKERCONNECT: kafka:29092

networks:
broker-kafka:
driver: bridge




Para probar el mensaje:
curl -X POST "http://localhost:8080/api/kafka/producer/publish?message=mensaje2"