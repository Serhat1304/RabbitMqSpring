package rabbbitMQSpring.rabbbitMQSpring.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import rabbbitMQSpring.rabbbitMQSpring.errors.MessageErrorHandler;

@Service
public class RabbitMQConsumer extends MessageErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consumer(Message message) {
        try {
            if (message.getBody() == null || message.getBody().length == 0) {
                throw new IllegalArgumentException("Le contenu du message est vide.");
            }
            log.info(String.format("Received message -> %s", message));
        } catch (Exception e) {
            handleMessageProcessingError(message, e);
        }
    }

}
