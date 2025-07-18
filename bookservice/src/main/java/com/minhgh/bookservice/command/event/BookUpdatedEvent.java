package com.minhgh.bookservice.command.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookUpdatedEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
