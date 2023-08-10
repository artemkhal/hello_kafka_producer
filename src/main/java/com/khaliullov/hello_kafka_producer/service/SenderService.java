package com.khaliullov.hello_kafka_producer.service;

import com.khaliullov.hello_kafka_producer.config.KafkaProducerConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String key, String message){
        val future = kafkaTemplate.send(KafkaProducerConfiguration.TOPIC_NAME_1, key, message);
        val result = future.join();
        log.info("\nOffset:{}\n" +
                "Partition:{}",result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
    }


    public void sendWithoutPart(String key, String message){
        val future = kafkaTemplate.send(KafkaProducerConfiguration.TOPIC_NAME_2, key, message);
        val result = future.join();
        log.info("\nSend msg without partition\n" +
                "Offset:{}\n" +
                "Partition:{}",result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
    }
}
