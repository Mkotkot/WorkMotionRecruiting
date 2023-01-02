package com.workmotion.employee.service;

import com.workmotion.employee.exception.EmployeeNotFound;
import com.workmotion.employee.model.Status;
import com.workmotion.employee.model.requests.EmployeeRequestDTO;
import com.workmotion.employee.model.response.EmployeeResponseDTO;

import java.util.List;

/**
 * @author Mohamed.kotkot
 */
public interface EmployeeService {


    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestBody);

    EmployeeResponseDTO updateEmployee(String empId, EmployeeRequestDTO employeeRequestDTO) throws EmployeeNotFound;

    List<EmployeeResponseDTO> getAllEmployee();

    EmployeeResponseDTO updateStatus(String id, Status status) throws EmployeeNotFound;
}
