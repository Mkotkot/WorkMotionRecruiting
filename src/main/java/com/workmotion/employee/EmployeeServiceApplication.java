package com.workmotion.employee;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.config.EnableStateMachine;

/**
 * @author Mohamed.kotkot
 */

@SpringBootApplication
@EnableStateMachine
@OpenAPIDefinition(info = @Info(title = "Rest Employee service", version = "1.0", description = "Demo App For Rest API"))
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }
}
