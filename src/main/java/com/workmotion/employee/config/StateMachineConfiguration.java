package com.workmotion.employee.config;

import com.workmotion.employee.model.Event;
import com.workmotion.employee.model.Status;
import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;

/**
 *
 *
 * @author Mohamed.kotkot
 */
@Configuration
@EnableStateMachine
public class StateMachineConfiguration {

    @Bean
    public StateMachine<Status, Event> stateMachine(StateMachineListener<Status, Event> listener) throws Exception {
        StateMachineBuilder.Builder<Status, Event> builder = StateMachineBuilder.builder();

        builder.configureStates()
            .withStates()
            .initial(Status.ADDED)
            .states(EnumSet.allOf(Status.class));

        builder.configureTransitions().withExternal()
            .source(Status.ADDED).target(Status.IN_CHECK)
            .event(Event.START_PROCESS)
            .and()

            .withExternal()
            .source(Status.IN_CHECK).target(Status.APPROVED)
            .event(Event.APPROVED)
            .and()

            .withExternal()
            .source(Status.APPROVED).target(Status.IN_CHECK)
            .event(Event.IN_PROGRESS)
            .and()

            .withExternal().source(Status.ADDED).target(Status.APPROVED)
            .event(Event.APPROVED)
            .and()

            .withExternal().source(Status.APPROVED).target(Status.ACTIVE)
            .event(Event.PUBLISH_STATUS);


        StateMachine<Status, Event> stateMachine = builder.build();
        stateMachine.addStateListener(new Listener());
        return stateMachine;
    }

}