package gov.iti.jets.rabbitmq.consumer.controller;

import gov.iti.jets.rabbitmq.consumer.listener.MessageListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageListener messageListener;

    public MessageController(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Map<String, String>>> getMessages() {
        return ResponseEntity.ok(messageListener.getMessages());
    }
}
