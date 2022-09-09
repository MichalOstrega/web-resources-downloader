package com.most.web.resources.downloader.adapters.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaMessageProducer implements MessageProducer {

    private final KafkaTemplate<String, WebResourceMessage> kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String topicName;

    @Override
    public void sendMessage(WebResourceMessage msg) {
        kafkaTemplate.send(topicName, msg);
    }
}
