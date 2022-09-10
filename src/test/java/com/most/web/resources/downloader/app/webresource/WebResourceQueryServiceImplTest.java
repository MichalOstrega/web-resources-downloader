package com.most.web.resources.downloader.app.webresource;

import static org.assertj.core.api.Assertions.assertThat;

import com.most.web.resources.downloader.app.webresource.dto.WebResourceDto;
import com.most.web.resources.downloader.app.webresource.dto.WebResourceWithoutDataDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@ExtendWith(SpringExtension.class)
class WebResourceQueryServiceImplTest {

    @TestConfiguration
    static class WebResourceQueryServiceImplTestContextConfiguration {
        @Bean
        public WebResourceQueryService queryService(WebResourceQueryRepository repository) {
            return new WebResourceQueryServiceImpl(repository);
        }
    }

    @Autowired
    private WebResourceQueryService queryService;

    @MockBean
    private WebResourceQueryRepository repository;

    @BeforeEach
    public void setUp() throws SQLException {
        WebResourceDto google = createWebResourceDto(0L,"http://www.google.com");
        WebResourceDto onet = createWebResourceDto(1L, "http://www.onet.pl");
        WebResourceDto weszlo = createWebResourceDto(2L, "http://www.weszlo.com");

        Set<WebResourceWithoutDataDto> allWebResources = Stream.of(google, onet, weszlo)
                .map(this::mapToWebResourceWithoutDataDto)
                .collect(Collectors.toSet());

        Mockito.when(repository.findDtoById(0)).thenReturn(Optional.of(google));
        Mockito.when(repository.findDtoById(-1L)).thenReturn(Optional.empty());
        Mockito.when(repository.findBy(WebResourceWithoutDataDto.class)).thenReturn(allWebResources);
    }



    @Test
    public void whenValidId_thenWebResourceShouldBeFound() {
        long id = 0L;
        Optional<WebResourceDto> dto = queryService.findById(id);
        assertThat(dto).isNotEmpty();
        assertThat(dto.get().getId()).isEqualTo(0L);

        verifyFindByIdIsCalledOnce(id);
    }

    @Test
    public void whenInValidId_thenWebResourceShouldNotBeFound() {
        long id = -1L;
        Optional<WebResourceDto> dto = queryService.findById(id);
        assertThat(dto).isEmpty();

        verifyFindByIdIsCalledOnce(id);
    }

    @Test
    public void given3WebResources_whenFindAll_thenReturn3Records() throws SQLException {
        Set<WebResourceWithoutDataDto> webResources = queryService.findAll();
        assertThat(webResources).hasSize(3);

        verifyFindAllIsCalledOnce();
    }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findDtoById(id);
        Mockito.reset(repository);
    }

    private void verifyFindAllIsCalledOnce() {
        Mockito.verify(repository, VerificationModeFactory.times(1)).findBy(WebResourceWithoutDataDto.class);
        Mockito.reset(repository);
    }

    private WebResourceWithoutDataDto mapToWebResourceWithoutDataDto(WebResourceDto dto) {
        return WebResourceWithoutDataDto.create(dto.getId(), dto.getUrl(), dto.getUuid(), dto.getContentType(), dto.getCreationTime());
    }

    private WebResourceDto createWebResourceDto(Long id, String url) throws SQLException {
        return WebResourceDto.create(id, url, randomUUID(), TEXT_HTML_VALUE, new SerialBlob(new byte[0]), now());
    }
}