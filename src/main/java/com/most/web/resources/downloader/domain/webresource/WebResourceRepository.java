package com.most.web.resources.downloader.domain.webresource;

import org.springframework.stereotype.Repository;

@Repository
public interface WebResourceRepository {

    WebResource save(WebResource webResource);
}
