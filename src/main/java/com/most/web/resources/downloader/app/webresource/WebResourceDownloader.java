package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.app.webresource.httpClient.DownloadResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface WebResourceDownloader {

    DownloadResponse download(String url) throws IOException, ExecutionException, InterruptedException;
}
