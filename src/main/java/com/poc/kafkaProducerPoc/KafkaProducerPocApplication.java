package com.poc.kafkaProducerPoc;

import com.poc.kafkaProducerPoc.dto.KafkaProducerDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaProducerPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerPocApplication.class, args);
	}


}
