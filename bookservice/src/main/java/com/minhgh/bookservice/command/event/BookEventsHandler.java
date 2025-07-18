package com.minhgh.bookservice.command.event;

import com.minhgh.bookservice.command.data.Book;
import com.minhgh.bookservice.command.data.BookRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookEventsHandler {

    private final BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event) {
        var book = Book.builder()
                .id(event.getId())
                .name(event.getName())
                .author(event.getAuthor())
                .isReady(event.getIsReady())
                .build();

        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookDeletedEvent event) {
        System.out.println("BookEventsHandler BookDeletedEvent event: " + event.getId());

        var book = bookRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        bookRepository.delete(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event) {
        System.out.println("BookEventsHandler BookUpdatedEvent event: " + event.getId());

        var book = bookRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setIsReady(event.getIsReady());

        bookRepository.save(book);
    }
}
