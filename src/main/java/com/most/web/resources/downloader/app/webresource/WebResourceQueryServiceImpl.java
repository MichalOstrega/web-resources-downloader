package com.most.web.resources.downloader.app.webresource;

import com.most.web.resources.downloader.app.webresource.dto.WebResourceDto;
import com.most.web.resources.downloader.app.webresource.dto.WebResourceWithoutDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
class WebResourceQueryServiceImpl implements WebResourceQueryService {

    private final WebResourceQueryRepository repository;

    @Override
    public Set<WebResourceWithoutDataDto> findAll() {
        return repository.findBy(WebResourceWithoutDataDto.class);
    }

    @Override
    public Optional<WebResourceDto> findById(Long id) {
        return repository.findDtoById(id);
    }
}
