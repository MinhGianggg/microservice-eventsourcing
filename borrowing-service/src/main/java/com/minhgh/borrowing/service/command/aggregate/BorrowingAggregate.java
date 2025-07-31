package com.minhgh.borrowing.service.command.aggregate;

import com.minhgh.borrowing.service.command.command.CreateBorrowingCommand;
import com.minhgh.borrowing.service.command.event.BorrowingCreatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.time.DateUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Aggregate
@NoArgsConstructor
@Getter
@Setter
public class BorrowingAggregate {

    @AggregateIdentifier
    private String id;

    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returningDate;

    @CommandHandler
    public BorrowingAggregate(CreateBorrowingCommand command) {
        var event = new BorrowingCreatedEvent(
                command.getId(),
                command.getBookId(),
                command.getEmployeeId(),
                command.getBorrowingDate(),
//                new Date()
                DateUtils.addDays(command.getBorrowingDate(), 30)
        );

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(BorrowingCreatedEvent event) {
        this.id = event.getId();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployeeId();
        this.borrowingDate = event.getBorrowingDate();
        this.returningDate = event.getReturningDate();
    }
}
