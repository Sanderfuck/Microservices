package com.sander.microservices.notification.kafka;

import com.sander.microservices.clients.message.MessageRequest;
import com.sander.microservices.clients.notification.NotificationRequest;
import com.sander.microservices.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListeners {
    private final NotificationService notificationService;

    @KafkaListener(
        topicPartitions = @TopicPartition(
            topic = "sander",
            partitions = {"0"}),
        groupId = "main-group")
    public void messageListenerMainGroup(String message) {
        log.info("Main Group! Received string message: {}", message);
        notificationService.send(message);
    }

    @KafkaListener(
        topicPartitions = @TopicPartition(
            topic = "sander",
            partitions = {"1"}),
        groupId = "main-group")
    public void notificationRequestListenerMainGroup(NotificationRequest request) {
        log.info("Main Group! Received notification request: {}", request);
        notificationService.send(request.toString());
    }

    @KafkaListener(
        topicPartitions = @TopicPartition(
            topic = "sander",
            partitions = {"2"}),
        groupId = "main-group")
    public void messageRequestMultiTypeListenerMainGroup(MessageRequest request) {
        log.info("Main Group! Received Message request: {}", request);
        notificationService.send(request);
    }

    //Other consumer group that receive parallel the same messages

    @KafkaListener(
        topicPartitions = @TopicPartition(
            topic = "sander",
            partitions = {"0"}),
        groupId = "backup-group")
    public void messageListenerBackupGroup(String message) {
        log.info("Backup Group! Received notification message: {}", message);
        notificationService.send(message);
    }

    @KafkaListener(
        topicPartitions = @TopicPartition(
            topic = "sander",
            partitions = {"1"}),
        groupId = "backup-group")
    public void notificationRequestListenerBackupGroup(NotificationRequest request) {
        log.info("Backup Group! Received notification request: {}", request);
        notificationService.send(request.toString());
    }

    @KafkaListener(
        topicPartitions = @TopicPartition(
            topic = "sander",
            partitions = {"2"}),
        groupId = "backup-group")
    public void messageRequestMultiTypeListenerBackupGroup(MessageRequest request) {
        log.info("Backup Group! Received Message request: {}", request);
        notificationService.send(request);
    }
}
