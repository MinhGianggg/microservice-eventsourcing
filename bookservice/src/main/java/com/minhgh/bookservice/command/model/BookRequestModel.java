package com.minhgh.bookservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequestModel(
        @NotBlank(message = "Name is mandatory")
        @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
        String name,

        @NotBlank(message = "Author is mandatory")
        String author,
        Boolean isReady) {}
