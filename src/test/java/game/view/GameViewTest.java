package game.view;

import game.config.GameConfig;
import game.constants.RacingGameMessage;
import game.exception.GameException;
import game.model.RacingCar;
import game.model.RacingGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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

        gameView.print(1);
        gameView.print("test");
        gameView.print(new Object());
        racingGame.nextAllStep();

        gameView.printNewLine();
        gameView.print(racingGame);
        gameView.printNewLine();

        Arrays.stream(racingCarArray).forEach(gameView::print);

        gameView.print(new Exception("Exception"));
        gameView.print(new RuntimeException("RuntimeException01"));
        gameView.print(new GameException(new RuntimeException("RuntimeException02")));
        gameView.print(new GameException(RacingGameMessage.EXCEPTION_FORMAT, "테스트 예외 상황"));
    }

    @DisplayName("출력_함수_예외_발생_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 출력_함수_GameException_출력_중_예외_발생_테스트(String language) {
        init(language);
        Arrays.stream(RacingGameMessage.values())
                .filter(messageKey -> messageKey.getArgumentsClass().length > 0)
                .forEach(messageKey ->
                        assertThatExceptionOfType(GameException.class)
                                .isThrownBy(() -> {
                                    GameException e = new GameException(messageKey);
                                    gameView.print(e);
                                })
                );
    }


    private void init(String language) {
        config = GameConfig.builder()
                .locale(language)
                .build();
        gameView = new GameView(config);
    }
}
