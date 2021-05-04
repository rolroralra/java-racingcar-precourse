package game.view;

import game.config.GameConfig;
import game.exception.GameException;
import game.exception.GameExceptionCode;
import game.model.RacingCar;
import game.model.RacingGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Random;

public class GameViewTest {
    private GameConfig config;
    private GameView gameView;
    private int totalTryCount;
    private RacingCar[] racingCarArray;
    private RacingGame racingGame;

    @BeforeEach
    void setUp() {
        racingCarArray = new RacingCar[] {
                new RacingCar("bmw"),
                new RacingCar("benz"),
                new RacingCar("쏘나타"),
                new RacingCar("audi")
        };

        totalTryCount = new Random().nextInt(20) + 1;

        racingGame = new RacingGame(totalTryCount, racingCarArray);
    }

    @DisplayName("출력_함수_정상작동_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 출력_함수_정상작동_테스트(String language) {
        init(language);

        gameView.println(1);
        gameView.println("test");
        gameView.println(new Object());
        racingGame.nextAllStep();

        gameView.println();
        gameView.println(racingGame);
        gameView.println();

        Arrays.stream(racingCarArray).forEach(gameView::println);

        gameView.println(new Exception("Exception"));
        gameView.println(new RuntimeException("RuntimeException01"));
        gameView.println(new GameException(new RuntimeException("RuntimeException02")));
        gameView.println(new GameException(GameExceptionCode.RACING_CAR_INVALID_NAME, racingCarArray[0]));
        gameView.println(new GameException(GameExceptionCode.RACING_GAME_INVALID, racingGame));
        gameView.println(new GameException(GameExceptionCode.RACING_GAME_NOT_COMPLETED, racingGame));
    }

    @DisplayName("출력_함수_예외_발생_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 출력_함수_GameException_출력_중_예외_발생_테스트(String language) {
//        init(language);
//        Arrays.stream(RacingGameMessage.values())
//                .filter(messageKey -> messageKey.getArgumentsClass().length > 0)
//                .forEach(messageKey ->
//                        assertThatExceptionOfType(RacingGameException.class)
//                                .isThrownBy(() -> {
//                                    RacingGameException e = new RacingGameException(messageKey);
//                                    gameView.println(e);
//                                })
//                );
    }


    private void init(String language) {
        config = GameConfig.builder()
                .locale(language)
                .build();
        gameView = new GameView(config);
    }
}
