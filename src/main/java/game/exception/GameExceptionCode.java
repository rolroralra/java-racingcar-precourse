package game.exception;

import game.constants.GameMessage;
import game.model.RacingCar;
import game.model.RacingGame;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.function.Function;

@Getter
public enum GameExceptionCode {
    RACING_CAR_INVALID_NAME(
            "1001",
            GameMessage.RACING_CAR_INVALID_NAME_EXCEPTION_FORMAT,
            RacingCar.class,
            new Class[]{
                    Integer.class,
                    String.class
            },
            (racingCar) -> new Object[]{RacingCar.MAX_NAME_LENGTH, ((RacingCar)racingCar).getName()}
    ),
    RACING_GAME_NOT_COMPLETED(
            "1002",
            GameMessage.RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT,
            RacingGame.class,
            new Class[]{
                    Integer.class,
                    Integer.class
            },
            (racingGame) -> {
                int totalTryCount = ((RacingGame) racingGame).getTotalTryCount();
                int tryCount = ((RacingGame) racingGame).getTryCount();
                return new Object[]{totalTryCount, tryCount};
            }
    ),
    RACING_GAME_INVALID(
            "1003",
            GameMessage.RACING_GAME_INVALID_EXCEPTION_FORMAT,
            RacingGame.class,
            new Class[]{},
            (racingGame) -> new Object[]{}
    ),
    GAME_INTERNAL(
            "1004",
            GameMessage.GAME_INTERNAL_EXCEPTION_FORMAT,
            Exception.class,
            new Class[]{
                    String.class
            },
            (e) -> {
                try {
                    return new Object[]{Exception.class.getMethod("getMessage").invoke(e)};
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e2) {
                    throw new GameException(e2);
                }
            }
    );

    private final String code;
    private final GameMessage gameMessage;
    private final Class<?> dtoClass;
    private final Class<?>[] argumentClasses;
    private final Function<Object, Object[]> formatter;

    GameExceptionCode(String code, GameMessage gameMessage, Class<?> dtoClass, Class<?>[] argumentClasses, Function<Object, Object[]> formatter) {
        this.code = code;
        this.gameMessage = gameMessage;
        this.dtoClass = dtoClass;
        this.argumentClasses = argumentClasses;
        this.formatter = formatter;
    }

    public boolean checkDtoObjectValid(Object object) {
        return this.dtoClass.isInstance(object)
                && checkFormatArgumentsValid(getFormatArguments(object));
    }

    public boolean checkFormatArgumentsValid(Object[] arguments) {
        if (this.argumentClasses.length != arguments.length) {
            return false;
        }

        for (int i = 0; i < argumentClasses.length; i++) {
            if (!argumentClasses[i].isInstance(arguments[i])) {
                return false;
            }
        }

        return true;
    }

    public String getMessageKey() {
        return this.gameMessage.getKey();
    }

    public Object[] getFormatArguments(Object object) {
        return this.formatter.apply(object);
    }

    @Override
    public String toString() {
        return "RacingGameExceptionCode{" + "code='" + code + '\'' +
                ", racingGameMessage='" + gameMessage + '\'' +
                ", dtoClass=" + dtoClass +
                ", argumentClasses=" + Arrays.toString(argumentClasses) +
                '}';
    }
}
