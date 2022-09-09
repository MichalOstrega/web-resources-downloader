package com.most.web.resources.downloader.adapters.messages;

public interface MessageProducer {

    void sendMessage(WebResourceMessage msg);
}
