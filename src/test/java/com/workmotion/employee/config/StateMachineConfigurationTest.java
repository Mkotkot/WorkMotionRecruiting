package com.workmotion.employee.config;

import com.workmotion.employee.model.Event;
import com.workmotion.employee.model.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;


@SpringBootTest
class StateMachineConfigurationTest {


    @Autowired
    private StateMachine<Status, Event> stateMachine;

    @Test
    @Order(1)
    public void initTest() {

        Assertions.assertThat(stateMachine.getInitialState().getId()).isEqualTo(Status.ADDED);

        Assertions.assertThat(stateMachine).isNotNull();
    }

    @Test
    @Order(2)
    public void testGreenFlow() {

        stateMachine.sendEvent(Event.IN_PROGRESS);
        stateMachine.sendEvent(Event.APPROVED);
        stateMachine.sendEvent(Event.PUBLISH_STATUS);

        Assertions.assertThat(stateMachine.getState().getId())
            .isEqualTo(Status.ACTIVE);
    }

}