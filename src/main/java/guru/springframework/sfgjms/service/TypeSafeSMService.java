package guru.springframework.sfgjms.service;

import com.typesafe.config.Config;
import guru.springframework.sfgjms.builder.DayStateMachineBuilder;
import guru.springframework.sfgjms.entity.Child;
import guru.springframework.sfgjms.entity.StateMachineWrapper;
import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TypeSafeSMService {
    @Autowired
    private DayStateMachineBuilder builder;
    @Autowired
    private ChildService childService;

    public StateMachine<ChildDay, ChildEvent> receiveSM(Config config) throws Exception {
        Child child = childService.findById(config.getLong("id"));
        System.out.println(child.toString());

        List<? extends Config> childConfigs = config.getConfigList("children");

        for (Config childConfig : childConfigs) {
            ChildDay source = ChildDay.valueOf(childConfig.getString("source"));
            ChildDay target = ChildDay.valueOf(childConfig.getString("target"));
            ChildEvent event = ChildEvent.valueOf(childConfig.getString("event"));

            builder.build(child, source, target, event);
        }

        return builder.buildMachine();
    }

    public void setNewDay(Child child) {
        child.setDay(ChildDay.NEW);
        childService.save(child);
    }


}
