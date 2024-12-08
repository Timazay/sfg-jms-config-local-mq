package guru.springframework.sfgjms.service;

import com.typesafe.config.Config;
import guru.springframework.sfgjms.builder.ActionChildEvent;
import guru.springframework.sfgjms.builder.DayStateMachineBuilder;
import guru.springframework.sfgjms.entity.Child;
import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;


@Service
public class TypeSafeSMService {
    @Autowired
    private DayStateMachineBuilder builder;
    @Autowired
    private ChildService childService;

    public StateMachine<ChildDay, ChildEvent> receiveSM(Config config) throws Exception {
        Child child = childService.findById(config.getLong("input.id"));
        ActionChildEvent ac = new ActionChildEvent();
        ChildDay source = ChildDay.valueOf(config.getString("input.source"));
        ChildDay target = ChildDay.valueOf(config.getString("input.target"));
        ChildEvent event = ChildEvent.valueOf(config.getString("input.event"));

        builder.build(source, target, event, ac.action(child, event));
        return builder.buildMachine();
    }

}
