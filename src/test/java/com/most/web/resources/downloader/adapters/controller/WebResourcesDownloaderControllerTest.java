package com.most.web.resources.downloader.adapters.controller;

import com.most.web.resources.downloader.adapters.controller.mapper.WebResourceRequestMapper;
import com.most.web.resources.downloader.adapters.messages.MessageProducer;
import com.most.web.resources.downloader.app.webresource.WebResourceQueryService;
import com.most.web.resources.downloader.app.webresource.dto.WebResourceDto;
import com.most.web.resources.downloader.app.webresource.dto.WebResourceWithoutDataDto;
import com.most.web.resources.downloader.utils.BlobUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = WebResourcesDownloaderController.class)
@AutoConfigureMockMvc
class WebResourcesDownloaderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageProducer messageProducer;
    @MockBean
    private WebResourceRequestMapper mapper;
    @MockBean
    private WebResourceQueryService queryService;
    @MockBean
    private BlobUtil blobUtil;


    @Test
    public void whenPostValidWebResources_thenReturnStatusOk() throws Exception {
        WebResourceRequestDto dto = new WebResourceRequestDto("http://www.google.com");

        mvc.perform(post("/webResources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPostInValidWebResources_thenReturnStatusIsBadRequest() throws Exception {
        WebResourceRequestDto dto = new WebResourceRequestDto("some wrong url");

        mvc.perform(post("/webResources")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations[0].fieldName", is("url")))
                .andExpect(jsonPath("$.violations[0].message", is("Url is invalid")));
    }

    @Test
    public void givenWebResourcesWithoutDataDto_whenGetWebResources_thenReturnJsonArrayAndStatusIsOk() throws Exception {
        WebResourceWithoutDataDto google = createWebResourceWithoutDataDto(0L, "http://www.google.com");
        WebResourceWithoutDataDto onet = createWebResourceWithoutDataDto(1L, "http://www.onet.pl");
        WebResourceWithoutDataDto weszlo = createWebResourceWithoutDataDto(2L, "http://www.weszlo.com");

        Set<WebResourceWithoutDataDto> allWebResources = new HashSet(Arrays.asList(google, onet, weszlo));

        given(queryService.findAll()).willReturn(allWebResources);

        mvc.perform(get("/webResources"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        verifyFindAllIsCalledOnce();
    }

    @Test
    public void givenWebResourcesDto_whenGetWebResource_thenReturnJsonArrayAndStatusIsOk() throws Exception {
        long id = 0L;
        WebResourceDto google = createWebResourceDto(id, "http://www.google.com");

        given(queryService.findById(id)).willReturn(Optional.of(google));

        mvc.perform(get("/webResources/0"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,google.getContentType()))
                .andExpect(jsonPath("$").doesNotExist());

        verifyFindByIdIsCalledOnce(id);
    }

    @Test
    public void givenWebResourcesDto_whenGetInvalidWebResource_thenReturnStatusIsNotFound() throws Exception {
        long id = 0L;
        given(queryService.findById(id)).willReturn(Optional.empty());

        mvc.perform(get("/webResources/0"))
                .andExpect(status().isNotFound());
        verifyFindByIdIsCalledOnce(id);
    }

    private void verifyFindByIdIsCalledOnce(Long id) {
        Mockito.verify(queryService, VerificationModeFactory.times(1)).findById(id);
        Mockito.reset(queryService);
    }

    private void verifyFindAllIsCalledOnce() {
        Mockito.verify(queryService, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(queryService);
    }

    private WebResourceDto createWebResourceDto(Long id, String url) throws SQLException {
        return WebResourceDto.create(id, url, randomUUID(), TEXT_HTML_VALUE, new SerialBlob(new byte[0]), now());
    }

    private WebResourceWithoutDataDto createWebResourceWithoutDataDto(Long id, String url) throws SQLException {
        return WebResourceWithoutDataDto.create(id, url, randomUUID(), TEXT_HTML_VALUE,  now());
    }

}