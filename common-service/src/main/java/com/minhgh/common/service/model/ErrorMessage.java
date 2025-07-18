package com.minhgh.common.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private String code;
    private String message;
    private HttpStatus status;
}
