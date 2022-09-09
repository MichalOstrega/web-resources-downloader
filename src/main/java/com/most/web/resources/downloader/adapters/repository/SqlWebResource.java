package com.most.web.resources.downloader.adapters.repository;

import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
class SqlWebResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String url;

    @Column(columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column
    @Lob
    private Blob data;

    @Column
    private LocalDateTime creationTime;

    @Column
    private String contentType;
}
