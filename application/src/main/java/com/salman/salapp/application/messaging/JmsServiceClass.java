package com.salman.salapp.application.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsServiceClass {

    JmsTemplate jmsTemplate;

    @Value("${jms.queue}")
    String jmsQueue;

    public JmsServiceClass(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(jmsQueue, message);
    }
}
