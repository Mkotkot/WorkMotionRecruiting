package com.workmotion.employee.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.workmotion.employee.UtilTest;
import com.workmotion.employee.mapper.EmployeeMapper;
import com.workmotion.employee.model.Event;
import com.workmotion.employee.model.Status;
import com.workmotion.employee.service.EmployeeService;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private StateMachine<Status, Event> stateMachine;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        EmployeeController employeeController = new EmployeeController(employeeService, stateMachine);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void getEmployees() throws Exception {
        Mockito.when(employeeService.getAllEmployee())
                .thenReturn(List.of(EmployeeMapper.fromEmployeeEntityToEmployeeResponse(UtilTest.getEmployeeEntity(Status.ADDED))));
        mockMvc.perform(
                        get("/employees/")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void createEmployee() throws Exception {

        String request = UtilTest.mapper.writeValueAsString(UtilTest.getEmployeeRequestBody(Status.ADDED));
        Mockito.when(employeeService.createEmployee(Mockito.any()))
                .thenReturn(EmployeeMapper.fromEmployeeEntityToEmployeeResponse(UtilTest.getEmployeeEntity(Status.ADDED)));
        mockMvc.perform(
                post("/employees/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(request))
                .andExpect(status().isOk());
    }

    @Test
    void updateStatus() throws Exception {
        String request = UtilTest.mapper.writeValueAsString(UtilTest.getEmployeeRequestStatusBody(Status.ADDED));

        Mockito.when(employeeService.updateStatus(Mockito.any(), Mockito.any(Status.class)))
                .thenReturn(EmployeeMapper.fromEmployeeEntityToEmployeeResponse(UtilTest.getEmployeeEntity(Status.ADDED)));
        mockMvc.perform(
                put("/employees/1/status")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(request))
                .andExpect(status().isAccepted());
    }
}