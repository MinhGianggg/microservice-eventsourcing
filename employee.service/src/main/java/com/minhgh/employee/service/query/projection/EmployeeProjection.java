package com.minhgh.employee.service.query.projection;

import com.minhgh.employee.service.command.data.EmployeeRepository;
import com.minhgh.employee.service.query.model.EmployeeResponseModel;
import com.minhgh.employee.service.query.queries.GetAllEmployeeQuery;
import com.minhgh.employee.service.query.queries.GetDetailEmployeeQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeProjection {

    private final EmployeeRepository employeeRepository;

    @QueryHandler
    public List<EmployeeResponseModel> handle(GetAllEmployeeQuery query) {
        var employees = employeeRepository.findAllByIsDisciplined(query.getIsDisciplined());

        var response = employees.stream()
                .map(em -> new EmployeeResponseModel(
                        em.getId(), em.getFirstName(), em.getLastName(), em.getKin(), em.getIsDisciplined()))
                .toList();

        return response;
    }

    @QueryHandler
    public EmployeeResponseModel handle(GetDetailEmployeeQuery query) {
        var em = employeeRepository.findById(query.getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return new EmployeeResponseModel(
                em.getId(), em.getFirstName(), em.getLastName(), em.getKin(), em.getIsDisciplined());
    }
}
