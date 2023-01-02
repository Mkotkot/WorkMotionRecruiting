package com.workmotion.employee.controller;


import com.workmotion.employee.exception.EmployeeNotFound;
import com.workmotion.employee.model.Event;
import com.workmotion.employee.model.Status;
import com.workmotion.employee.model.requests.EmployeeRequestDTO;
import com.workmotion.employee.model.requests.EmployeeStatusRequestDTO;
import com.workmotion.employee.model.response.EmployeeResponseDTO;
import com.workmotion.employee.service.EmployeeService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mohamed.kotkot
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final StateMachine<Status, Event> stateMachine;

    public EmployeeController(EmployeeService employeeService,
                              StateMachine<Status, Event> stateMachine) {
        this.employeeService = employeeService;
        this.stateMachine = stateMachine;
        this.stateMachine.start();
    }

    @GetMapping("/")
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeResponseDTO> saveEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable("employeeId") String employeeId,@RequestBody @Valid EmployeeRequestDTO employeeRequestBody) {
        try {
            return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employeeRequestBody),
                    HttpStatus.OK);
        } catch (EmployeeNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{employeeId}/status")
    public ResponseEntity<EmployeeResponseDTO> updateStatus(@PathVariable("employeeId") String employeeId,@RequestBody @Valid EmployeeStatusRequestDTO employeeStatusRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO;
        try {
            employeeResponseDTO = employeeService.updateStatus(employeeId, employeeStatusRequestDTO.getStatus());
        } catch (EmployeeNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        switch (employeeStatusRequestDTO.getStatus()) {
            case IN_CHECK: {
                stateMachine.sendEvent(Event.IN_PROGRESS);
                break;
            }
            case APPROVED: {
                stateMachine.sendEvent(Event.APPROVED);
                break;
            }
            case ACTIVE: {
                stateMachine.sendEvent(Event.PUBLISH_STATUS);
                break;
            }
            case ADDED:
            default:
                break;
        }
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.ACCEPTED);
    }
}
