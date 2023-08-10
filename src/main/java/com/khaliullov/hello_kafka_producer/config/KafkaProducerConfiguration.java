package com.khaliullov.hello_kafka_producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    public static final String TOPIC_NAME_1 = "topic_with_partition";
    public static final String TOPIC_NAME_2 = "topic_without_partition";

    @Value("${spring.kafka.bootstrap-servers}")
    private String addresses;

    @Bean
    public NewTopic topicWithPartitions(){
        return TopicBuilder.name(TOPIC_NAME_1)
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topicWithoutPartitions(){
        return TopicBuilder.name(TOPIC_NAME_2)
                .build();
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, addresses);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaStringTemplate(ProducerFactory<String, String> factory){
        return new KafkaTemplate<>(factory);
    }
}
