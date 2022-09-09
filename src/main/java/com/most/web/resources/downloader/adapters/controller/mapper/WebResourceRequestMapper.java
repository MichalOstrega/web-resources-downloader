package com.most.web.resources.downloader.adapters.controller.mapper;


import com.most.web.resources.downloader.adapters.controller.WebResourceRequestDto;
import com.most.web.resources.downloader.adapters.messages.WebResourceMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface WebResourceRequestMapper {
    WebResourceMessage webResourceRequestDtoToWebResourceMessage(WebResourceRequestDto requestDto);

}
