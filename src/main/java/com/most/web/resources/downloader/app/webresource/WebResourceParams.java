package com.most.web.resources.downloader.app.webresource;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WebResourceParams {

    private String url;
    private byte[] data;
    private String contentType;
}
