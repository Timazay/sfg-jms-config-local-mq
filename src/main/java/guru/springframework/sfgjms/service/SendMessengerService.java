package guru.springframework.sfgjms.service;

import com.typesafe.config.Config;
import guru.springframework.sfgjms.util.ConfigProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;



@Service
public class SendMessengerService {
    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMsg() {

        Config config = ConfigProvider.configReader;
        jmsTemplate.convertAndSend("ChildQueue", msg);
    }

}