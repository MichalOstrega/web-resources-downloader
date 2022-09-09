package com.most.web.resources.downloader.adapters.controller.validation;

import lombok.Data;

@Data
public class Violation {
    private final String fieldName;

    private final String message;
}
