package com.poc.kafkaProducerPoc.controller;

import com.poc.kafkaProducerPoc.dto.KafkaProducerDTO;
import com.poc.kafkaProducerPoc.service.impl.KafkaProducerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka/producer")
@Slf4j
public class KafkaProducerController {

    private static final String TOPIC = "pockafka";

    @Autowired
    private KafkaProducerServiceImpl kafkaProducerService;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam("message")String message) {
        KafkaProducerDTO producer = new KafkaProducerDTO("HEADER",message);
        try{
            System.out.println("----> Producer Controller: "+producer + ", topic:"+ TOPIC + "  FIN ");
            kafkaProducerService.sendMessage(TOPIC, producer);
            return "Mensaje enviado al tópico '" + TOPIC + "': " + message;
         } catch (Exception e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());
            e.printStackTrace();
            return "Error al enviar el mensaje: " + e.getMessage();
        }
    }





}
