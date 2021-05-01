package game.exception;

import game.constants.MessageKey;
import lombok.Getter;

@Getter
public class GameException extends RuntimeException {
    private MessageKey messageKey;
    private Object[] arguments;

    public GameException(Exception e) {
        this(MessageKey.EXCEPTION_FORMAT, e.getMessage());
    }

    public GameException(MessageKey messageKey, Object... arguments) {
        this.messageKey = messageKey;
        this.arguments = arguments;
    }

}
