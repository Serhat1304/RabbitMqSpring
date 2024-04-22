package rabbbitMQSpring.rabbbitMQSpring.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import rabbbitMQSpring.rabbbitMQSpring.model.User;

public class MessageErrorHandler {

    private static Logger log = LoggerFactory.getLogger(MessageErrorHandler.class);

    public void handleMessageProcessingError(Message message, Exception exception) {
        log.error("Erreur de traitement du message: {}", new String(message.getBody()), exception);
    }

    public void handleJsonProcessingError(User user, Exception exception) {
        log.error("Erreur de traitement du JSON - Utilisateur: {}, Exception: {}", user.toString(), exception);
    }
}
