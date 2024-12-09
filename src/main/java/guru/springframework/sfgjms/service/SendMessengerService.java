package guru.springframework.sfgjms.service;

import com.typesafe.config.Config;
import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.entity.StateMachineWrapper;
import guru.springframework.sfgjms.util.ConfigProvider;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class SendMessengerService {
    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMsg(long childId, List<StateMachineWrapper> list) {
        Config config = ConfigProvider.createChildDayConf(childId, list);
        String msg = config.toString();
        int startIndex = msg.indexOf('{');
        int endIndex = msg.lastIndexOf('}');
        jmsTemplate.convertAndSend(JmsConfig.CHILD_QUEUE, msg.substring(startIndex, endIndex + 1));
    }


    public Config receive() {
        String message = (String) jmsTemplate.receiveAndConvert(JmsConfig.CHILD_QUEUE);
        log.info(message);
        return ConfigProvider.readConfString(message);

    }

}

