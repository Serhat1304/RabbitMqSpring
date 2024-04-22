package rabbbitMQSpring.rabbbitMQSpring.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbbitMQSpring.rabbbitMQSpring.errors.MessageErrorHandler;
import rabbbitMQSpring.rabbbitMQSpring.model.User;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final MessageErrorHandler messageErrorHandler;

    public RabbitMQJsonConsumer(MessageErrorHandler messageErrorHandler) {
        this.messageErrorHandler = messageErrorHandler;
    }

    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void consumeJsonMessage(User user) {
        try {
            if (user.getFirstName() == null || user.getLastName() == null) {
                throw new IllegalArgumentException("Le nom de l'utilisateur est vide.");
            }
            log.info(String.format("Received JSON message -> %s", user.toString()));
        } catch (Exception e) {
            messageErrorHandler.handleJsonProcessingError(user,e);
        }
    }
}
