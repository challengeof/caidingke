package net.caidingke.rabbitmq.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author bowen
 * @create 2016-11-03 10:49
 */
@Service
@Log
public class FanoutListener implements MessageListener {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onMessage(Message message) {
        log.info(String.format("接收到消息：%s", message.toString()));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JsonObject jsonObject = objectMapper.readValue(message.getBody(), JsonObject.class);
            log.info(jsonObject.toString());
            log.info(String.format(String.format("jsonObject name : == %s", jsonObject.getName())));
            log.info(String.format("jsonObject age : == %s", jsonObject.getAge()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
