package com.minhgh.employee.service.command.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeCreatedEvent {

    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
