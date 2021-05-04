package game.exception;

import game.model.RacingCar;
import game.model.RacingGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class GameExceptionTest {
    private List<RacingCar> racingCarList;
    private RacingGame racingGame;

    @BeforeEach
    void setUp() {
        racingCarList = new ArrayList<>();
        racingCarList.add(new RacingCar("bmw"));
        racingCarList.add(new RacingCar("test"));

        racingGame = new RacingGame(10, racingCarList);
    }

    @DisplayName("예외_생성자_정상_테스트")
    @Test
    void 예외_생성자_정상_테스트() {
        printException(new GameException(GameExceptionCode.RACING_GAME_INVALID, racingGame));

        printException(new GameException(GameExceptionCode.RACING_GAME_NOT_COMPLETED, racingGame));

        racingCarList.forEach(racingCar ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_CAR_INVALID_NAME,
                                racingCar)
                )
        );

        printException(
                new GameException(
                        GameExceptionCode.GAME_INTERNAL,
                        new RuntimeException("Test Runtime Exception.")
                )
        );

        printException(
                new GameException(
                        new RuntimeException("Test Runtime Exception 2")
                )
        );

        printException(new GameException("Just Test Error Message"));
    }

    @DisplayName("예외_생성자_예외_케이스_테스트")
    @Test
    void 예외_생성자_예외_케이스_테스트() {
        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_GAME_INVALID,
                                null
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_GAME_INVALID,
                                new Object()
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_GAME_INVALID,
                                racingCarList.get(0)
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_CAR_INVALID_NAME,
                                null
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_CAR_INVALID_NAME,
                                new Object()
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_CAR_INVALID_NAME,
                                racingGame
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_GAME_NOT_COMPLETED,
                                null
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_GAME_NOT_COMPLETED,
                                new Object()
                        )
                )
        );

        assertThatExceptionOfType(GameException.class).isThrownBy(() ->
                printException(
                        new GameException(
                                GameExceptionCode.RACING_GAME_NOT_COMPLETED,
                                racingCarList.get(0)
                        )
                )
        );
    }

    private void printException(GameException e) {
//        System.out.println(e.toString());
//        System.out.println(e.getExceptionCode());
//        System.out.println(Arrays.toString(e.getArguments()));
        System.out.println(e.getExceptionCode().getCode());
        System.out.println(e.getMessage());
        System.out.println(e.getMessage(Locale.ENGLISH));
//        System.out.println(e.getMessage(Locale.KOREAN));
        System.out.println(Arrays.toString(e.getExceptionCode().getArgumentClasses()));
        System.out.println(e.getExceptionCode().getDtoClass());
//        System.out.println(e.getExceptionCode().getFormatter());
        System.out.println(e.getExceptionCode().getGameMessage());
        System.out.println();
    }
}