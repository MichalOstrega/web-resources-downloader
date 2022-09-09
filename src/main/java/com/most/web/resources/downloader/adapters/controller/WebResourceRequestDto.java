package com.most.web.resources.downloader.adapters.controller;

import com.most.web.resources.downloader.adapters.controller.validation.URL;
import lombok.Data;


@Data
public class WebResourceRequestDto {

    @URL
    String url;
}
