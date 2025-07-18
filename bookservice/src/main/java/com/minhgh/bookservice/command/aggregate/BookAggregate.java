package com.minhgh.bookservice.command.aggregate;

import com.minhgh.bookservice.command.command.CreateBookCommand;
import com.minhgh.bookservice.command.command.DeleteBookCommand;
import com.minhgh.bookservice.command.command.UpdateBookCommand;
import com.minhgh.bookservice.command.event.BookCreatedEvent;
import com.minhgh.bookservice.command.event.BookDeletedEvent;
import com.minhgh.bookservice.command.event.BookUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
public class BookAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand command) {
        var bookCreatedEvent = BookCreatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .author(command.getAuthor())
                .isReady(command.getIsReady())
                .build();

        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @CommandHandler
    public void handle(UpdateBookCommand command) {
        var bookUpdatedEvent = BookUpdatedEvent.builder()
                .id(command.getId())
                .name(command.getName())
                .author(command.getAuthor())
                .isReady(command.getIsReady())
                .build();

        AggregateLifecycle.apply(bookUpdatedEvent);
    }

    @CommandHandler
    public void handle(DeleteBookCommand command) {
        var bookDeletedEvent = BookDeletedEvent.builder()
                .id(command.getId())
                .build();

        AggregateLifecycle.apply(bookDeletedEvent);
    }

    @EventSourcingHandler
    public void on(BookCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookUpdatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookDeletedEvent event) {
        this.id = event.getId();
    }
}
