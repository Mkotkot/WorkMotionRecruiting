package com.workmotion.employee;

import com.workmotion.employee.model.EmployeeEntity;
import com.workmotion.employee.model.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.employee.model.requests.EmployeeRequestDTO;
import com.workmotion.employee.model.requests.EmployeeStatusRequestDTO;

/**
 * @author Mohamed.kotkot
 */
public class UtilTest {


    public static ObjectMapper mapper = new ObjectMapper();

    public static EmployeeRequestDTO getEmployeeRequestBody(Status status) {
        EmployeeRequestDTO requestDTO = new EmployeeRequestDTO();
        requestDTO.setName("name");
        requestDTO.setAge("10");
        requestDTO.setLocation("Cairo");
        requestDTO.setStatus(status);
        return requestDTO;
    }
    public static EmployeeStatusRequestDTO getEmployeeRequestStatusBody(Status status) {
        EmployeeStatusRequestDTO requestDTO = new EmployeeStatusRequestDTO();
        requestDTO.setStatus(status);
        return requestDTO;
    }

    public static EmployeeEntity getEmployeeEntity(Status status) {
        return EmployeeEntity.builder().id(10L).name("name").age("10").location("Cairo").status(status).build();
    }
}
