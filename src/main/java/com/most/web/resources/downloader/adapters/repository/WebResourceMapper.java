package com.most.web.resources.downloader.adapters.repository;


import com.most.web.resources.downloader.domain.webresource.WebResource;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface WebResourceMapper {
    SqlWebResource webResourceToSqlWebResource(WebResource webResource);
    WebResource sqlWebResourceToWebResource(SqlWebResource sqlWebResource);
}
