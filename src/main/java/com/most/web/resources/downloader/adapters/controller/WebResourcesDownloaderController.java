package com.most.web.resources.downloader.adapters.controller;

import com.most.web.resources.downloader.adapters.controller.mapper.WebResourceRequestMapper;
import com.most.web.resources.downloader.adapters.messages.MessageProducer;
import com.most.web.resources.downloader.app.webresource.WebResourceQueryService;
import com.most.web.resources.downloader.app.webresource.dto.WebResourceWithoutDataDto;
import com.most.web.resources.downloader.utils.BlobUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Blob;
import java.util.Set;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/webResources")
@RequiredArgsConstructor
class WebResourcesDownloaderController {

    private final MessageProducer messageProducer;
    private final WebResourceRequestMapper mapper;
    private final WebResourceQueryService queryService;
    private final BlobUtil blobUtil;


    @PostMapping
    ResponseEntity<String> createWebResources(@RequestBody @Valid WebResourceRequestDto requestDto) {
        messageProducer.sendMessage(mapper.webResourceRequestDtoToWebResourceMessage(requestDto));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<Set<WebResourceWithoutDataDto>> getCreatedWebResources() {
        return ResponseEntity.ok().body(queryService.findAll());
    }


    @GetMapping("/{id}")
    ResponseEntity getResource(@PathVariable Long id) {
        return queryService.findById(id)
                .map(dto -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, dto.getContentType())
                        .body(blobUtil.retrieveBytes(dto.getData())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}
