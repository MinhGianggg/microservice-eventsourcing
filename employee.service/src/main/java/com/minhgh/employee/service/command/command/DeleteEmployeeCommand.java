package com.minhgh.employee.service.command.command;

import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Getter
public class DeleteEmployeeCommand {

    @TargetAggregateIdentifier
    private String id;
}
