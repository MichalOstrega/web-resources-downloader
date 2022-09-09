package com.most.web.resources.downloader.app.webresource.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(as = WebResourceDto.DeserializationImpl.class)
public interface WebResourceDto {
    long getId();
    String getUrl();
    UUID getUuid();
    Blob getData() ;
    String getContentType();
    LocalDateTime getCreationTime();

    class DeserializationImpl implements WebResourceDto {
        private long id;
        private String url;
        private UUID uuid;
        private Blob data;
        private String contentType;
        private LocalDateTime creationTime;

        @Override
        public long getId() {
            return id;
        }

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public UUID getUuid() {
            return uuid;
        }

        @Override
        public Blob getData() {
            return data;
        }

        @Override
        public String getContentType() {
            return contentType;
        }

        @Override
        public LocalDateTime getCreationTime() {
            return creationTime;
        }




    }
}