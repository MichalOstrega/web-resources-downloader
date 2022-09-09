package com.most.web.resources.downloader.domain.webresource;

import lombok.Builder;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class WebResource {

    private long id;
    private String url;
    private UUID uuid;
    private Blob data;
    private LocalDateTime creationTime;
    private String contentType;
}
