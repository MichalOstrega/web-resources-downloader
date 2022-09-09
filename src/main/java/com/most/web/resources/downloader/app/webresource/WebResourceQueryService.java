package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.app.webresource.dto.WebResourceDto;
import com.most.web.resources.downloader.app.webresource.dto.WebResourceWithoutDataDto;

import java.util.Optional;
import java.util.Set;

public interface WebResourceQueryService {

    Set<WebResourceWithoutDataDto> findAll();

    Optional<WebResourceDto> findById(Long id);
}
