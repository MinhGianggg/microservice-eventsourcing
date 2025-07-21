package com.minhgh.employee.service.query.model;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
public class EmployeeResponseModel {
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
