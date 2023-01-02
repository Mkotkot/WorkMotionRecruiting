package com.workmotion.employee.mapper;

import com.workmotion.employee.model.EmployeeEntity;
import com.workmotion.employee.model.requests.EmployeeRequestDTO;
import com.workmotion.employee.model.response.EmployeeResponseDTO;

/**
 *
 *
 * @author Mohamed.kotkot
 */
public final class EmployeeMapper {


    private EmployeeMapper() {
        super();
    }

    public static EmployeeEntity fromEmployeeDtoToEmployeeEntity(EmployeeRequestDTO requestBody) {
        return EmployeeEntity.builder().name(requestBody.getName()).age(requestBody.getAge())
                .location(requestBody.getLocation()).status(requestBody.getStatus()).build();
    }

    public static EmployeeResponseDTO fromEmployeeEntityToEmployeeResponse(EmployeeEntity entity) {
        return EmployeeResponseDTO.builder().id(entity.getId()).name(entity.getName()).age(entity.getAge())
                .location(entity.getLocation()).status(entity.getStatus()).build();
    }

}
