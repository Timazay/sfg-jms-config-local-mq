package guru.springframework.sfgjms.builder;


import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
public class DayStateMachineBuilder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private StateMachineBuilder.Builder<ChildDay, ChildEvent> builder;

    public DayStateMachineBuilder() {
        this.builder = StateMachineBuilder.builder();
    }


    public StateMachine<ChildDay, ChildEvent> buildMachine() throws Exception {
       /* StateMachineBuilder.Builder<ChildDay, ChildEvent> builder = StateMachineBuilder.builder();*/


        builder.configureStates()
                .withStates()
                .initial(ChildDay.NEW)
                .state(ChildDay.WEEKEND)
                .state(ChildDay.WEEKDAY)
                .end(ChildDay.END);

        builder.configureConfiguration()
                .withConfiguration()
                .listener(listener())
                .autoStartup(true);
     /*   builder.configureTransitions().withExternal().source(ChildDay.NEW).target(ChildDay.NEW).event(ChildEvent.LEISURE_ACTIVITY);*/
        return builder.build();
    }

    public DayStateMachineBuilder build(ChildDay source, ChildDay target, ChildEvent event,
                                        Action<ChildDay, ChildEvent> action) throws Exception {

        if (action != null) {
            builder.configureTransitions()
                    .withExternal()
                    .source(source)
                    .target(target)
                    .event(event)
                    .action(action);
        } else {
            builder.configureTransitions()
                    .withExternal()
                    .source(source)
                    .target(target)
                    .event(event);
        }


        return this;
    }

    public StateMachineListener<ChildDay, ChildEvent> listener() {
        return new StateMachineListenerAdapter<ChildDay, ChildEvent>() {
            @Override
            public void stateChanged(State<ChildDay, ChildEvent> from, State<ChildDay, ChildEvent> to) {
                logger.info("State change from " + from.getIds() + " to " + to.getIds());
            }
        };
    }

}
