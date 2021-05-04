package game.exception;

import lombok.Getter;

import java.util.Arrays;

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
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameThrowable{");
        sb.append("exceptionCode=").append(exceptionCode);
        sb.append(", arguments=").append(Arrays.toString(arguments));
        sb.append('}');
        return sb.toString();
    }
}
