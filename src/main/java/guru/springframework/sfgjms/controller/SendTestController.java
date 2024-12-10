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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log
public class SendTestController {
    @Autowired
    private TypeSafeSMService typeSafeSMService;
    @Autowired
    private SendMessengerService service;


// Первым вызывать /send он отправит json в mq с ребенком у которого id = 1
    @GetMapping("/send")
    public String sendGoToSchool(/*@RequestParam long childId*/) {
        List<StateMachineWrapper> confs = new ArrayList<>();
        confs.add(new StateMachineWrapper(ChildDay.NEW, ChildDay.WEEKDAY, ChildEvent.GOING_TO_SCHOOL));
        confs.add(new StateMachineWrapper(ChildDay.WEEKDAY, ChildDay.WEEKDAY, ChildEvent.DOING_HOMEWORK));
        confs.add(new StateMachineWrapper(ChildDay.WEEKDAY, ChildDay.END, ChildEvent.COMPLETE));
        service.sendMsg(1, confs);
        return "success";
    }
// вторым вызвать /receive он получает json из mq, парсит его и первращает в конфиг стейт машины
// в логах будут видны вызванные ивенты и состояния    
    @GetMapping("/receive")
    public String receive() throws Exception {
        Config config = service.receive();
        StateMachine<ChildDay, ChildEvent> sm = typeSafeSMService.receiveSM(config);

        log.info(sm.getState().getId().toString());
        sm.sendEvent(ChildEvent.GOING_TO_SCHOOL);
        sm.sendEvent(ChildEvent.DOING_HOMEWORK);
        log.info(sm.getState().getId().toString());
        sm.sendEvent(ChildEvent.COMPLETE);
        log.info(sm.getState().getId().toString());
        return "Message receive";
    }
}
