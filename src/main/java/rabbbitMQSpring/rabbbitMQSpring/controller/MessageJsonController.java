package rabbbitMQSpring.rabbbitMQSpring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rabbbitMQSpring.rabbbitMQSpring.model.User;
import rabbbitMQSpring.rabbbitMQSpring.publisher.RabbitMQJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        jsonProducer.sendJsonMessage(user);
            return ResponseEntity.ok("Json Message sent to RabbitMq ...");
    }

}
