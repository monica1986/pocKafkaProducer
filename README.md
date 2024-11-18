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
Antes de  utilizar la aplicación, ejecuta el siguiente comando:



**Crea una red Docker compartida:** Primero, asegúrate de que tanto Kafka como Kafdrop estén conectados a la misma red Docker. Por ejemplo, crea una red llamada kafka-network:
bash

docker network create kafka-network

**Ejecuta Zookeeper:** Lanza un contenedor para Zookeeper en la red kafka-network:
bash

docker run -d --name zookeeper --network kafka-network \
-e ALLOW_ANONYMOUS_LOGIN=yes \
bitnami/zookeeper:latest

**Ejecuta Kafka:** Lanza un contenedor Kafka en la misma red y asegúrate de configurar correctamente las propiedades KAFKA_ADVERTISED_LISTENERS y KAFKA_LISTENERS:
bash

docker run -d --name kafka --network kafka-network \
-p 9092:9092 \
-e KAFKA_BROKER_ID=1 \
-e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
bitnami/kafka:latest

En esta configuración:
KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:9092 asegura que Kafka sea accesible por otros contenedores usando el alias kafka.

**Ejecuta Kafdrop:** Ahora lanza Kafdrop en la misma red, configurando el KAFKA_BROKERCONNECT para que apunte al alias del contenedor Kafka (kafka:9092):
bash

docker run -d --rm --name kafdrop --network kafka-network \
-p 9000:9000 \
-e KAFKA_BROKERCONNECT=kafka:9092 \
-e JVM_OPTS="-Xms32M -Xmx64M" \
obsidiandynamics/kafdrop:latest

Para probar el mensaje:
curl -X POST "http://localhost:8080/api/kafka/producer/publish?message=mensaje2"