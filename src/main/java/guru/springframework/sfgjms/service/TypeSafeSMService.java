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
        List<StateMachineWrapper> children = new ArrayList<>();
        Child child = childService.findById(config.getLong("id"));
        // Получаем список конфигураций детей
        List<? extends Config> childConfigs = config.getConfigList("children");
        // Проходим по каждой конфигурации ребенка и создаем объект ChildDayConf
        for (Config childConfig : childConfigs) {
            ChildDay source = ChildDay.valueOf(childConfig.getString("source"));
            ChildDay target = ChildDay.valueOf(childConfig.getString("target"));
            ChildEvent event = ChildEvent.valueOf(childConfig.getString("event"));
            // Создаем новый объект ChildDayConf и добавляем его в список
            builder.build(child, source, target, event);


        }

 /*       Child child = childService.findById(config.getLong("child.id"));
        ChildDay source = ChildDay.valueOf(config.getString("child.source"));
        ChildDay target = ChildDay.valueOf(config.getString("child.target"));
        ChildEvent event = ChildEvent.valueOf(config.getString("child.event"));*/

        return builder.buildMachine();
    }

    public void setNewDay(Child child) {
        child.setDay(ChildDay.NEW);
        childService.save(child);
    }


}
