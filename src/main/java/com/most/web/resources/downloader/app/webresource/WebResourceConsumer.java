package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.adapters.messages.WebResourceMessage;

public interface WebResourceConsumer {
    void consume(WebResourceMessage message);
}
