package com.minhgh.bookservice.command.controller;

import com.minhgh.bookservice.command.command.CreateBookCommand;
import com.minhgh.bookservice.command.command.DeleteBookCommand;
import com.minhgh.bookservice.command.command.UpdateBookCommand;
import com.minhgh.bookservice.command.model.BookRequestModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String addBook(@Valid @RequestBody BookRequestModel model) {
        var command = CreateBookCommand.builder()
                .id(UUID.randomUUID().toString())
                .name(model.name())
                .author(model.author())
                .isReady(true)
                .build();

        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{bookId}")
    public String updateBook(@PathVariable String bookId, @Valid @RequestBody BookRequestModel model) {
        var command = UpdateBookCommand.builder()
                .id(bookId)
                .name(model.name())
                .author(model.author())
                .isReady(model.isReady())
                .build();

        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        var command = DeleteBookCommand.builder()
                .id(bookId)
                .build();

        return commandGateway.sendAndWait(command);
    }
}
