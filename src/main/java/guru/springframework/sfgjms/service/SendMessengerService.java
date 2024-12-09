package guru.springframework.sfgjms.service;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValueFactory;
import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.util.ConfigProvider;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.UUID;


@Log
@Service
public class SendMessengerService {
    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMsg(Config config) {
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
        /*Message message = (Message) jmsTemplate.receiveAndConvert(JmsConfig.CHILD_QUEUE);
        if (message == null) {
            return null;
        }
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);

            return ConfigProvider.readConfString(jsonMessage);
        } catch (JmsException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
}

