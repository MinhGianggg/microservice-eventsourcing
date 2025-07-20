package com.minhgh.employee.service.command.event;

import com.minhgh.employee.service.command.data.Employee;
import com.minhgh.employee.service.command.data.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventsHandler {

    private final EmployeeRepository employeeRepository;

    @EventHandler
    public void on(EmployeeCreatedEvent event) {
        var employee = Employee.builder()
                .id(event.getId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .kin(event.getKin())
                .isDisciplined(event.getIsDisciplined())
                .build();

        employeeRepository.save(employee);
    }

    @EventHandler
    public void on(EmployeeUpdatedEvent event) {
        var employee = employeeRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setId(event.getId());
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setKin(event.getKin());
        employee.setIsDisciplined(event.getIsDisciplined());

        employeeRepository.save(employee);
    }

    @EventHandler
    @DisallowReplay
    public void on(EmployeeDeletedEvent event) {
        var employee = employeeRepository.findById(event.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(employee);
    }
}
