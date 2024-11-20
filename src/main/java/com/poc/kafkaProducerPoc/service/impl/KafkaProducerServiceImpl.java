package com.poc.kafkaProducerPoc.service.impl;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.poc.kafkaProducerPoc.dto.KafkaProducerDTO;
import com.poc.kafkaProducerPoc.service.KafkaProduceService;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class KafkaProducerServiceImpl implements KafkaProduceService {

    @Autowired
    private KafkaTemplate<String, KafkaProducerDTO> kafkaTemplate;

    public void sendMessage(String topic, KafkaProducerDTO message) throws ExecutionException, InterruptedException {
        System.out.println("ProducerService.sendMessage() topic : " + topic +", mensaje: " + message.toString() );

        //System.out.println("ProducerService.createTopic()  : "  );
        //createTopic();

        //System.out.println("ProducerService.getTopic()  : " );
        //getTopic();

        try {
            kafkaTemplate.send(topic, message);
        }catch (Exception e) {
            System.out.println("Error al enviar el mensaje a Kafka: {} " +e.getMessage().toString()+ ".-- "+ e.getMessage());
        }
    }

    public void getTopic() throws ExecutionException, InterruptedException {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.18.0.3:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        System.out.println("ProducerService.getTopic() config : " + config.toString());
        //busco los topicos
        AdminClient client = AdminClient.create(config);
        ListTopicsResult result = client.listTopics();
        Collection<String> topics = result.names().get();
        for (String topic : topics) {
            System.out.println("Topico encontrado : " + topic);
        }

        client.close();

    }

    public void createTopic()  {
        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.18.0.3:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        System.out.println("ProducerService.createTopic() config : " + config.toString());

        try {
            AdminClient client = AdminClient.create(config);
            short replicationFactor = 1;
            NewTopic newTopic = new NewTopic("topicoCreadoJava", 1, replicationFactor);
            ArrayList<NewTopic> newTopics = new ArrayList<>();
            newTopics.add(newTopic);
            client.createTopics(newTopics);
            client.close();
        }catch (Exception e) {
            System.out.println("Error al crear el topico a Kafka: {}" + e.getMessage());
        }


    }

}
