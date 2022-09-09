package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.app.webresource.httpClient.DownloadResponse;
import com.most.web.resources.downloader.app.webresource.httpClient.HttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
class WebResourceDownloaderImpl implements WebResourceDownloader {

    private final HttpClient client;

    @Override
    public DownloadResponse download(String url) throws IOException, ExecutionException, InterruptedException {
        return client.download(url);
    }
}
