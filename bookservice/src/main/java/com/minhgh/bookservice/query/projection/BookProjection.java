package com.minhgh.bookservice.query.projection;

import com.minhgh.bookservice.command.data.BookRepository;
import com.minhgh.bookservice.query.model.BookResponseModel;
import com.minhgh.bookservice.query.queries.GetAllBooksQuery;
import com.minhgh.bookservice.query.queries.GetBookDetailQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookProjection {

    private final BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery query) {
        var books = bookRepository.findAll();

        return books.stream()
                .map(book -> new BookResponseModel(book.getName(), book.getAuthor(), book.getIsReady()))
                .toList();
    }

    @QueryHandler
    public BookResponseModel handle(GetBookDetailQuery query) {
        var book = bookRepository.findById(query.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found!"));

        return new BookResponseModel(book.getName(), book.getAuthor(), book.getIsReady());
    }
}
