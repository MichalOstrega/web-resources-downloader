package com.most.web.resources.downloader.app.webresource.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(as = WebResourceDto.DeserializationImpl.class)
public interface WebResourceDto {
    static WebResourceDto create(final long id,
                                            final String url,
                                            final UUID uuid,
                                            final String contentType,
                                            final Blob data,
                                            final LocalDateTime creationTime) {
        return new WebResourceDto.DeserializationImpl(id,url,uuid,data,contentType,creationTime);
    }

    long getId();
    String getUrl();
    UUID getUuid();
    Blob getData() ;
    String getContentType();
    LocalDateTime getCreationTime();

    @AllArgsConstructor
    @NoArgsConstructor
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