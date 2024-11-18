package com.poc.kafkaProducerPoc.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KafkaProducerDTO {

    private static final long serialVersionID = 1L;

    private String header;

    private String body;

}
