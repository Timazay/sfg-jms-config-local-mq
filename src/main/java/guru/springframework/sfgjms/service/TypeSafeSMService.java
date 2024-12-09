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
        System.out.println(config.toString());
        Child child = childService.findById(config.getLong("child.id"));
        ChildDay source = ChildDay.valueOf(config.getString("child.source"));
        ChildDay target = ChildDay.valueOf(config.getString("child.target"));
        ChildEvent event = ChildEvent.valueOf(config.getString("child.event"));
        System.out.println(source);
        System.out.println(target);
        System.out.println(event);
        builder.build(child, source, target, event);
        return builder.buildMachine();
    }

    public void setNewDay(Child child){
        child.setDay(ChildDay.NEW);
        childService.save(child);
    }


}
