package game.exception;

import game.constants.RacingGameMessage;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class GameException extends RuntimeException {
    private RacingGameMessage racingGameMessage;
    private Object[] arguments;

    public GameException(String message) {
        super(message);
    }

    public GameException(Exception e) {
        this(RacingGameMessage.EXCEPTION_FORMAT, e.getMessage());
    }

    public GameException(RacingGameMessage racingGameMessage, Object... arguments) {
        if (!checkValid(racingGameMessage, arguments)) {
            throw new GameException(
                    RacingGameMessage.GAME_INTERNAL_EXCEPTION_FORMAT,
                    String.format(
                            "MessageKey and Arguments are invalid. [%s] [%s]",
                            racingGameMessage,
                            Arrays.toString(arguments)
                    )
            );
        }

        this.racingGameMessage = racingGameMessage;
        this.arguments = arguments;
    }

    public static boolean checkValid(RacingGameMessage racingGameMessage, Object... arguments) {
        if (racingGameMessage == null) {
            return false;
        }

        Class<?>[] argumentClasses = racingGameMessage.getArgumentsClass();

        if (arguments == null || argumentClasses.length != arguments.length) {
            return false;
        }

        for (int i = 0; i < argumentClasses.length; i++) {
            if (!argumentClasses[i].isInstance(arguments[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getMessage() {
        if (checkValid(racingGameMessage, arguments)) {
            return String.format(racingGameMessage.getSampleMessage(), arguments);
        }

        return super.getMessage();
    }
}
