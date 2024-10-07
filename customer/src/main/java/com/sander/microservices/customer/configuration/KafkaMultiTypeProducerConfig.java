package com.sander.microservices.customer.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaMultiTypeProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> multiTypeProducerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(JsonSerializer.TYPE_MAPPINGS,
            "notificationRequest:com.sander.microservices.clients.notification.NotificationRequest, " +
                "messageRequest:com.sander.microservices.clients.message.MessageRequest");
        return props;
    }

    @Bean
    public ProducerFactory<String, Object> multiTypeProducerFactory() {
        return new DefaultKafkaProducerFactory<>(multiTypeProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, Object> myltiTypeKafkaTemplate(
        ProducerFactory<String, Object> multiTypeProducerFactory) {
        return new KafkaTemplate<>(multiTypeProducerFactory);
    }
}
