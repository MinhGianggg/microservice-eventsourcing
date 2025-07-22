package com.minhgh.employee.service.query.controller;

import com.minhgh.employee.service.query.model.EmployeeResponseModel;
import com.minhgh.employee.service.query.queries.GetAllEmployeeQuery;
import com.minhgh.employee.service.query.queries.GetDetailEmployeeQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Employee Query")
public class EmployeeQueryController {

    private final QueryGateway queryGateway;

    @Operation(
            summary = "Get list employee",
            description = "List employee filter by isDisciplined",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized / Invalid Token"
                    )
            }
    )
    @GetMapping
    public List<EmployeeResponseModel> getAllEmployee(
            @RequestParam(required = false, defaultValue = "false") Boolean isDisciplined) {
        var response = queryGateway.query(
                        new GetAllEmployeeQuery(isDisciplined),
                        ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class))
                .join();

        return response;
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponseModel getEmployee(@PathVariable String employeeId) {
        var response = queryGateway.query(
                        new GetDetailEmployeeQuery(employeeId),
                        ResponseTypes.instanceOf(EmployeeResponseModel.class))
                .join();

        return response;
    }
}
