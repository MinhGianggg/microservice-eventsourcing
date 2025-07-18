package com.minhgh.bookservice.command.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class DeleteBookCommand {

    @TargetAggregateIdentifier
    private String id;
}
