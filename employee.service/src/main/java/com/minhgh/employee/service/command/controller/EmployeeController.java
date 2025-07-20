package com.minhgh.employee.service.command.controller;

import com.minhgh.employee.service.command.command.CreateEmployeeCommand;
import com.minhgh.employee.service.command.command.DeleteEmployeeCommand;
import com.minhgh.employee.service.command.command.UpdateEmployeeCommand;
import com.minhgh.employee.service.command.model.CreateEmployeeModel;
import com.minhgh.employee.service.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel model) {
        var command = CreateEmployeeCommand.builder()
                .id(UUID.randomUUID().toString())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .kin(model.getKin())
                .isDisciplined(false)
                .build();

        return commandGateway.sendAndWait(command);
    }

    @PutMapping("/{employeeId}")
    public String updateEmployee(@PathVariable String employeeId, @Valid @RequestBody UpdateEmployeeModel model) {
        var command = UpdateEmployeeCommand.builder()
                .id(employeeId)
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .kin(model.getKin())
                .isDisciplined(model.getIsDisciplined())
                .build();

        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        var command = DeleteEmployeeCommand.builder()
                .id(employeeId)
                .build();

        return commandGateway.sendAndWait(command);
    }
}
