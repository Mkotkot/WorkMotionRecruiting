package com.workmotion.employee.model.response;

import com.workmotion.employee.model.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String age;
    private String location;
    private Status status;

}
