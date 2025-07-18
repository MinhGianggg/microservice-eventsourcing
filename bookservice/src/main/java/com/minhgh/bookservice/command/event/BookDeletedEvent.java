package com.minhgh.bookservice.command.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookDeletedEvent {
    private String id;
}
