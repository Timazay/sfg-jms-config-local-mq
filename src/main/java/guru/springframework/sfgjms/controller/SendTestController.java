package guru.springframework.sfgjms.controller;

import com.typesafe.config.Config;
import guru.springframework.sfgjms.entity.Child;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/school")
    public String sendEventGoToSchool(@RequestParam Long id) throws Exception {
  /*      Child anton = new Child();
        anton.setName("Anton");
        anton.setSurname("Mas");
        anton.setAge(11);
        childService.create(anton);*/
        Child child = childService.findById(id);
        Config config = ConfigProvider.configReader;
        StateMachine<ChildDay, ChildEvent> sm = typeSafeSMService.receiveSM(config);
        sm.sendEvent(ChildEvent.GOING_TO_SCHOOL);
        sm.start();
        return "Message sent with ID: " + id;
    }

    @GetMapping("/send")
    public String sendMsg(@RequestParam String msg) {
        service.sendMsg(msg);
        return "success";
    }

}
