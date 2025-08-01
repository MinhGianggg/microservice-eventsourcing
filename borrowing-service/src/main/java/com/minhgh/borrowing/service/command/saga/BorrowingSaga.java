package com.minhgh.borrowing.service.command.saga;

import com.minhgh.borrowing.service.command.command.DeleteBorrowingCommand;
import com.minhgh.borrowing.service.command.event.BorrowingCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class BorrowingSaga {

    @Autowired
    private transient CommandGateway commandGateway;
    @Autowired
    private transient QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    private void handle(BorrowingCreatedEvent event) {
        log.info("start saga with BorrowingCreatedEvent of borrowing-id: {}, book-id: {}, and employee-id: {}",
                event.getId(), event.getBookId(), event.getEmployeeId());

        try {

        } catch (Exception exception) {
            log.error(exception.getMessage());

            rollback(event.getId());
        }
    }

//    private void handler()

    private void rollback(String id) {
        var command = new DeleteBorrowingCommand(id);
        commandGateway.sendAndWait(command);

        SagaLifecycle.end();
    }
}
