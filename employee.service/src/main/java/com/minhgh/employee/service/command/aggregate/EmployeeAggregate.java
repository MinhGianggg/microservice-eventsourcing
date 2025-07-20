package com.minhgh.employee.service.command.aggregate;

import com.minhgh.employee.service.command.command.CreateEmployeeCommand;
import com.minhgh.employee.service.command.command.DeleteEmployeeCommand;
import com.minhgh.employee.service.command.command.UpdateEmployeeCommand;
import com.minhgh.employee.service.command.event.EmployeeCreatedEvent;
import com.minhgh.employee.service.command.event.EmployeeDeletedEvent;
import com.minhgh.employee.service.command.event.EmployeeUpdatedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Getter
public class EmployeeAggregate {

    @AggregateIdentifier
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand command) {
        var employeeCreatedEvent = EmployeeCreatedEvent.builder()
                .id(command.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .kin(command.getKin())
                .isDisciplined(command.getIsDisciplined())
                .build();

        AggregateLifecycle.apply(employeeCreatedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeCreatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @CommandHandler
    public void handle(UpdateEmployeeCommand command) {
        var employeeUpdatedEvent = EmployeeUpdatedEvent.builder()
                .id(command.getId())
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .kin(command.getKin())
                .isDisciplined(command.getIsDisciplined())
                .build();

        AggregateLifecycle.apply(employeeUpdatedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeUpdatedEvent event) {
        this.id = event.getId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @CommandHandler
    public void handle(DeleteEmployeeCommand command) {
        var employeeDeletedEvent = EmployeeDeletedEvent.builder()
                .id(command.getId())
                .build();

        AggregateLifecycle.apply(employeeDeletedEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeDeletedEvent event) {
        this.id = event.getId();
    }
}
