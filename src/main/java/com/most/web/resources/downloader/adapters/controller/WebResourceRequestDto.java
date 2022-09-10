package com.most.web.resources.downloader.adapters.controller;

import com.most.web.resources.downloader.adapters.controller.validation.URL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebResourceRequestDto {

    @URL
    String url;
}
