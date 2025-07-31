package com.minhgh.borrowing.service.command.event;

import com.minhgh.borrowing.service.command.data.Borrowing;
import com.minhgh.borrowing.service.command.data.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowingEventHandler {

    private final BorrowingRepository borrowingRepository;

    @EventHandler
    public void on(BorrowingCreatedEvent event) {
        var model = new Borrowing(
                event.getId(),
                event.getBookId(),
                event.getEmployeeId(),
                event.getBorrowingDate(),
                event.getReturningDate()
        );

        borrowingRepository.save(model);
    }
}
