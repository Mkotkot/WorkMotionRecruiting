package com.workmotion.employee.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.workmotion.employee.UtilTest;
import com.workmotion.employee.model.EmployeeEntity;
import com.workmotion.employee.model.Status;
import com.workmotion.employee.model.requests.EmployeeRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeMapperTest {


    @Test
    void fromEmployeeDtoToEmployeeEntityTest() {
        EmployeeRequestDTO employeeRequestBody = UtilTest.getEmployeeRequestBody(Status.ADDED);
        EmployeeEntity employeeEntity = EmployeeMapper.fromEmployeeDtoToEmployeeEntity(employeeRequestBody);

        assertEquals(employeeEntity.getStatus().name(), employeeRequestBody.getStatus().name());
        assertEquals(employeeEntity.getName(), employeeRequestBody.getName());
    }
}