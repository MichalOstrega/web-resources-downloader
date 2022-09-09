package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.app.webresource.dto.WebResourceDto;

import java.util.Optional;
import java.util.Set;

public interface WebResourceQueryRepository {

    Optional<WebResourceDto> findDtoById(long id);

    <T> Set<T> findBy(Class<T> clazz);

}
