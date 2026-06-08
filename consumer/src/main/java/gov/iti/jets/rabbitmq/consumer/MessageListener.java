package gov.iti.jets.rabbitmq.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MessageListener {

    private final List<Map<String, String>> messages = new CopyOnWriteArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String content) {
        System.out.println("Received: " + content);
        messages.add(Map.of(
                "content", content,
                "receivedAt", LocalDateTime.now().format(FORMATTER)
        ));
    }

    public List<Map<String, String>> getMessages() {
        return new ArrayList<>(messages);
    }
}
