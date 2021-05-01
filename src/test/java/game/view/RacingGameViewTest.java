package game.view;

import game.config.GameConfig;
import game.model.RacingCar;
import game.model.RacingGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RacingGameViewTest {
    private GameConfig config;
    private RacingGameView racingGameView;

    @DisplayName("레이싱카_입력_프롬프트_출력_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 레이싱카_입력_프롬프트_출력_테스트(String language) {
        config = GameConfig.builder()
                .locale(language)
                .build();
        racingGameView = new RacingGameView(config);

        racingGameView.printRacingCarsInputPrompt();
    }

    @DisplayName("총_시도_회수_입력_프롬프트_출력_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 총_시도_회수_입력_프롬프트_출력_테스트(String language) {
        config = GameConfig.builder()
                .locale(language)
                .build();
        racingGameView = new RacingGameView(config);

        racingGameView.printTotalTryCountInputPrompt();
    }

    @DisplayName("레이싱_최종_결과_프롬프트_출력_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 레이싱_최종_결과_프롬프트_출력_테스트(String language) {
        config = GameConfig.builder()
                .locale(language)
                .build();
        racingGameView = new RacingGameView(config);

        racingGameView.printRacingGameResultPrompt();
    }

    @DisplayName("레이싱_최종_결과_출력_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 레이싱_최종_결과_출력_테스트(String language) {
        config = GameConfig.builder()
                .locale(language)
                .build();
        racingGameView = new RacingGameView(config);

        racingGameView.printRacingGameResult(executeRacingGame());
    }

    @DisplayName("레이싱_게임_기록_출력_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    void 레이싱_게임_기록_출력_테스트(String language) {
        config = GameConfig.builder()
                .locale(language)
                .build();
        racingGameView = new RacingGameView(config);

        racingGameView.printRacingGameHistory(executeRacingGame());
    }

    private RacingGame executeRacingGame() {
        int totalTryCount = 10;

        RacingCar[] racingCarArray = new RacingCar[] {
                new RacingCar("bmw"),
                new RacingCar("benz"),
                new RacingCar("쏘나타"),
                new RacingCar("audi")
        };

        RacingGame racingGame = new RacingGame(totalTryCount, racingCarArray);
        racingGame.nextAllStep();
        return racingGame;
    }

}
