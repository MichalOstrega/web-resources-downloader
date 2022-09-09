package com.most.web.resources.downloader.app.webresource;

import com.github.f4b6a3.uuid.UuidCreator;
import com.most.web.resources.downloader.domain.webresource.WebResource;
import com.most.web.resources.downloader.domain.webresource.WebResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class WebResourceDatabaseCommandService implements WebResourceCommandService {

    private final WebResourceRepository repository;

    @Override
    public WebResource save(WebResourceParams params) throws SQLException {
            return repository.save(createWebResource(params));
    }

    private WebResource createWebResource(WebResourceParams params) throws SQLException {
        return WebResource.builder()
                .url(params.getUrl())
                .contentType(params.getContentType())
                .uuid(UuidCreator.getNameBasedSha1(params.getUrl()))
                .data(new SerialBlob(params.getData()))
                .creationTime(LocalDateTime.now())
                .build();
    }
}
