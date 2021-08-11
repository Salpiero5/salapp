package com.salman.salapp.application.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class JmsReceiver {

    @JmsListener(destination = "${jms.queue}")
    public void receiveMessage(String message) {
        System.out.println("from JmsListener: " + message);
    }
}
