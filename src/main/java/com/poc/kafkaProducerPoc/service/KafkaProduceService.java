package com.poc.kafkaProducerPoc.service;

import com.poc.kafkaProducerPoc.dto.KafkaProducerDTO;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;

public interface KafkaProduceService {


    public void sendMessage(String topic, KafkaProducerDTO message) throws ExecutionException, InterruptedException;

    public void getTopic() throws ExecutionException, InterruptedException;
}
