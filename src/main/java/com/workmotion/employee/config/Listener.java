package com.workmotion.employee.config;

import com.workmotion.employee.model.Event;
import com.workmotion.employee.model.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

/**
 *
 *
 * @author Mohamed.kotkot
 */
@Configuration
public class Listener extends StateMachineListenerAdapter<Status, Event> {

    // listen to the state value
    @Override
    public void stateChanged(State<Status, Event> from, State<Status, Event> to) {
        System.out.println("State changed to " + to.getId());
    }
}
