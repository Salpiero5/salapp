package com.salman.salapp.application.controller;

import com.salman.salapp.application.messaging.JmsServiceClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    JmsServiceClass jmsServiceClass;

    public MessageController(JmsServiceClass jmsServiceClass) {
        this.jmsServiceClass = jmsServiceClass;
    }

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String body) {
        jmsServiceClass.sendMessage(body);
        return new ResponseEntity<>("sent", HttpStatus.OK);
    }
}
