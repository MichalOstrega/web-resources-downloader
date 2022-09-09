package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.adapters.messages.WebResourceMessage;
import com.most.web.resources.downloader.app.webresource.httpClient.DownloadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
class WebResourceConsumerImpl implements WebResourceConsumer {

    private final WebResourceDownloader webResourceDownloader;
    private final WebResourceCommandService commandService;

    @Override
    public void consume(WebResourceMessage message) {
        try {
            DownloadResponse response = webResourceDownloader.download(message.getUrl());
            commandService.save(mapToWebResourceParams(response));
        } catch (SQLException | IOException | ExecutionException | InterruptedException e) {
            //TODO handle exception
            e.printStackTrace();
        }
    }

    private WebResourceParams mapToWebResourceParams(DownloadResponse response) {
        return WebResourceParams.builder()
                .data(response.getData().array())
                .contentType(response.getContentType())
                .url(response.getUrl())
                .build();
    }


}
