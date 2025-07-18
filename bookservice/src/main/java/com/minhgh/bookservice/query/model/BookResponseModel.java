package com.minhgh.bookservice.query.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookResponseModel {
    private String name;
    private String author;
    private Boolean isReady;
}