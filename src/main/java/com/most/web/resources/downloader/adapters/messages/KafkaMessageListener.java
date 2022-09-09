package com.most.web.resources.downloader.adapters.messages;

import com.most.web.resources.downloader.app.webresource.WebResourceConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class KafkaMessageListener {

    private final WebResourceConsumer consumer;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}")
    public void listenGroup1(WebResourceMessage message) {
        consumer.consume(message);
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}")
    public void listenGroup2(WebResourceMessage message) {
        consumer.consume(message);
    }

}
