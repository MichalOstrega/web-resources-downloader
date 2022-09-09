package com.most.web.resources.downloader.app.webresource.httpClient;

import lombok.Data;

import java.nio.ByteBuffer;

@Data
public class DownloadResponse {

    public DownloadResponse(String url) {
        this.url = url;
    }

    private String url;
    private ByteBuffer data;
    private String contentType;
}
