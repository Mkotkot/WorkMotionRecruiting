package com.workmotion.employee.service.impl;

import com.workmotion.employee.exception.EmployeeNotFound;
import com.workmotion.employee.mapper.EmployeeMapper;
import com.workmotion.employee.model.EmployeeEntity;
import com.workmotion.employee.model.Status;
import com.workmotion.employee.model.requests.EmployeeRequestDTO;
import com.workmotion.employee.model.response.EmployeeResponseDTO;
import com.workmotion.employee.repository.EmployeeRepository;
import com.workmotion.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestBody) {
        return EmployeeMapper.fromEmployeeEntityToEmployeeResponse(employeeRepository.save(EmployeeMapper.fromEmployeeDtoToEmployeeEntity(employeeRequestBody)));
    }

    public EmployeeResponseDTO updateEmployee(String empId, EmployeeRequestDTO employeeRequestBody) throws EmployeeNotFound {
        employeeRepository.findById(Long.parseLong(empId)).orElseThrow(() -> new EmployeeNotFound());
        EmployeeEntity employeeEntity = EmployeeMapper.fromEmployeeDtoToEmployeeEntity(employeeRequestBody);
        employeeEntity.setId(Long.valueOf(empId));
        return EmployeeMapper.fromEmployeeEntityToEmployeeResponse(employeeRepository.save(employeeEntity));
    }

    public List<EmployeeResponseDTO> getAllEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream().map(EmployeeMapper::fromEmployeeEntityToEmployeeResponse).collect(Collectors.toList());
    }


    public EmployeeResponseDTO updateStatus(String empId, Status status) throws EmployeeNotFound {
        EmployeeEntity employeeEntity = employeeRepository.findById(Long.parseLong(empId)).orElseThrow(() -> new EmployeeNotFound());
        employeeEntity.setStatus(status);
        return EmployeeMapper.fromEmployeeEntityToEmployeeResponse(employeeRepository.save(employeeEntity));

    }
}
