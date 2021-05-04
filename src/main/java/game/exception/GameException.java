package game.exception;

import game.config.GameConfig;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class GameException extends RuntimeException {
    private final GameExceptionCode exceptionCode;
    private final Object[] arguments;

    public GameException(String message) {
        super(message);
        this.exceptionCode = GameExceptionCode.GAME_INTERNAL;
        this.arguments = new Object[]{message};
    }

    public GameException(Exception e) {
        this(e.getMessage());
    }

    public GameException(GameExceptionCode exceptionCode, Object object) {
        if (!exceptionCode.checkDtoObjectValid(object)) {
            throw new GameException(this.getClass().getSimpleName() + "'s Constructor Invalid Parameters");
        }

        this.exceptionCode = exceptionCode;
        this.arguments = exceptionCode.getFormatArguments(object);
    }

    public String getMessageKey() {
        return this.exceptionCode.getMessageKey();
    }

    @Override
    public String getMessage() {
        return getMessage(Locale.getDefault());
    }

    public String getMessage(Locale locale) {
        return ResourceBundle.getBundle(GameConfig.MESSAGE_BUNDLE_NAME, locale).getString(getExceptionCode().getMessageKey());

    }

    @Override
    public String toString() {
        return "GameThrowable{" + "exceptionCode=" + exceptionCode +
                ", arguments=" + Arrays.toString(arguments) +
                '}';
    }
}
