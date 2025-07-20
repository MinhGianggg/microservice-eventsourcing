package com.minhgh.employee.service.command.event;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeDeletedEvent {
    private String id;
}
