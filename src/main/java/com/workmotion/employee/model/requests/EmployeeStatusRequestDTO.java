package com.workmotion.employee.model.requests;


import com.workmotion.employee.model.Status;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeeStatusRequestDTO {
    @NotNull
    @ApiParam(value = "status", allowableValues = "ADDED, IN_CHECK, APPROVED, ACTIVE")
    private Status status;
}
