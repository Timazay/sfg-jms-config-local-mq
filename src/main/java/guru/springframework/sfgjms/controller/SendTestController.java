package guru.springframework.sfgjms.controller;

import com.typesafe.config.Config;
import guru.springframework.sfgjms.entity.StateMachineWrapper;
import guru.springframework.sfgjms.entity.state.ChildDay;
import guru.springframework.sfgjms.entity.state.ChildEvent;
import guru.springframework.sfgjms.service.ChildService;
import guru.springframework.sfgjms.service.SendMessengerService;
import guru.springframework.sfgjms.service.TypeSafeSMService;
import guru.springframework.sfgjms.util.ConfigProvider;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log
public class SendTestController {
    /* @Autowired
     private ChildDayService childDayService;*/
    @Autowired
    private ChildService childService;
    @Autowired
    private TypeSafeSMService typeSafeSMService;
    @Autowired
    private SendMessengerService service;

    @GetMapping("/receive")
    public String receive() throws Exception {
     /*  Child anton = new Child();
        anton.setName("Anton");
        anton.setSurname("Mas");
        anton.setAge(11);
        typeSafeSMService.setNewDay(anton);
        childService.save(anton);*/
        Config config = service.receive();

        System.out.println(config.toString());
        StateMachine<ChildDay, ChildEvent> sm = typeSafeSMService.receiveSM(config);
        System.out.println(sm.getState().getId());
        sm.sendEvent(ChildEvent.GOING_TO_SCHOOL);
        sm.sendEvent(ChildEvent.DOING_HOMEWORK);
        System.out.println(sm.getState().getId());
        sm.sendEvent(ChildEvent.COMPLETE);
        System.out.println(sm.getState().getId());
        return "Message sent";
    }

    @GetMapping("/send")
    public String sendGoToSchool() {
        List<StateMachineWrapper> confs = new ArrayList<>();
        confs.add(new StateMachineWrapper(ChildDay.NEW, ChildDay.WEEKDAY, ChildEvent.GOING_TO_SCHOOL));
        confs.add(new StateMachineWrapper(ChildDay.WEEKDAY, ChildDay.WEEKDAY, ChildEvent.DOING_HOMEWORK));
        confs.add(new StateMachineWrapper(ChildDay.WEEKDAY, ChildDay.END, ChildEvent.COMPLETE));
        Config config = ConfigProvider.createConf(1, confs);
        service.sendMsg(config);
        return "success";
    }

    /*docker run --detach --name artemiscontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:latest-alpine*/
}
