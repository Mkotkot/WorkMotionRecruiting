package com.workmotion.employee.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.workmotion.employee.UtilTest;
import com.workmotion.employee.model.EmployeeEntity;
import com.workmotion.employee.model.Status;
import com.workmotion.employee.model.response.EmployeeResponseDTO;
import com.workmotion.employee.repository.EmployeeRepository;

import java.util.List;

import com.workmotion.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EmployeeServiceTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }


    @Test
    void createEmployee() {
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(UtilTest.getEmployeeEntity(Status.ADDED));
        EmployeeResponseDTO employee = employeeService.createEmployee(UtilTest.getEmployeeRequestBody(Status.ADDED));

        ArgumentCaptor<EmployeeEntity> employeeEntity = ArgumentCaptor.forClass(EmployeeEntity.class);
        verify(employeeRepository, times(1)).save(employeeEntity.capture());

        Assertions.assertEquals(employee.getName(), employeeEntity.getValue().getName());
    }

    @Test
    void getAllEmployee() {
        when(employeeRepository.findAll()).thenReturn(List.of(UtilTest.getEmployeeEntity(Status.ADDED)));
        List<EmployeeResponseDTO> allEmployee = employeeService.getAllEmployee();

        verify(employeeRepository, times(1)).findAll();
        Assertions.assertNotNull(allEmployee.get(0));

    }

    @Test
    void updateStatus() {
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(UtilTest.getEmployeeEntity(Status.APPROVED));
        EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(UtilTest.getEmployeeRequestBody(Status.APPROVED));

        ArgumentCaptor<EmployeeEntity> employeeEntity = ArgumentCaptor.forClass(EmployeeEntity.class);
        verify(employeeRepository, times(1)).save(employeeEntity.capture());
        Assertions.assertEquals(employeeResponseDTO.getStatus().name(), employeeEntity.getValue().getStatus().name());
    }
}