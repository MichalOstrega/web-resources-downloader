package com.most.web.resources.downloader.adapters.repository;

import com.most.web.resources.downloader.app.webresource.WebResourceQueryRepository;
import com.most.web.resources.downloader.domain.webresource.WebResource;
import com.most.web.resources.downloader.domain.webresource.WebResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;


public interface SqlWebResourceRepository extends JpaRepository<SqlWebResource, Long> {
}

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
class WebResourceRepositoryImpl implements WebResourceRepository {
    private final SqlWebResourceRepository repository;
    private final WebResourceMapper mapper;


    @Override
    public WebResource save(WebResource webResource) {
        SqlWebResource sqlWebResource = repository.save(mapper.webResourceToSqlWebResource(webResource));
        return mapper.sqlWebResourceToWebResource(sqlWebResource);
    }
}

interface SqlWebResourceQueryRepository extends WebResourceQueryRepository, Repository<SqlWebResource, Long> {
}