package com.most.web.resources.downloader.adapters.controller.validation;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponse {

    private final List<Violation> violations;

}
