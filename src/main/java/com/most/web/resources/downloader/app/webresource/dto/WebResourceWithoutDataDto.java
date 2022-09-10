package com.most.web.resources.downloader.app.webresource.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(as = WebResourceWithoutDataDto.DeserializationImpl.class)
public interface WebResourceWithoutDataDto {
    static WebResourceWithoutDataDto create(final long id,
                          final String url,
                          final UUID uuid,
                          final String contentType,
                          final LocalDateTime creationTime) {
        return new DeserializationImpl(id,url,uuid,contentType,creationTime);
    }


    long getId();
    String getUrl();
    UUID getUuid();
    String getContentType();
    LocalDateTime getCreationTime();

    @AllArgsConstructor
    @NoArgsConstructor
    class DeserializationImpl implements WebResourceWithoutDataDto {
        private long id;
        private String url;
        private UUID uuid;
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
        public String getContentType() {
            return contentType;
        }

        @Override
        public LocalDateTime getCreationTime() {
            return creationTime;
        }



    }
}