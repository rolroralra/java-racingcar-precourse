package game.controller;

import game.config.GameConfig;
import game.constants.RacingGameMessage;
import game.model.RacingCar;
import game.model.RacingGame;
import game.model.RacingHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameControllerTest {
    private static final int MAX_TOTAL_TRY_COUNT = 20;
    private RacingGameController game;
    private int userInputTotalTryCount;
    private List<RacingCar> userInputRacingCarList;
    private StringJoiner stringJoiner;

    @BeforeEach
    void setUp() {
        stringJoiner = new StringJoiner("\n", "", "\n");
    }

    @DisplayName("컨트롤러_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    public void 컨트롤러_테스트(String language) {
        String carNames = "bmw,audi,쏘나타,test";

        readUserInputCarNames(carNames);

        readUserInputTotalTryCount();

        GameConfig config = GameConfig.builder()
                .locale(language)
                .inputStream(getUserInputStream())
                .outputStream(System.out)
                .build();

        game = new RacingGameController(config);
        game.run();

        assertCompletedRacingGame(game.getRacingGame());
    }

    private void assertCompletedRacingGame(RacingGame racingGame) {
        // RacingGame 기본 정보 체크
        assertThat(racingGame.getTryCount()).isEqualTo(userInputTotalTryCount);
        assertThat(racingGame.getTotalTryCount()).isEqualTo(userInputTotalTryCount);
        assertThat(racingGame.getRacingCarList()).asList().hasSameSizeAs(userInputRacingCarList);
        assertThat(racingGame.toString().split("\n").length).isEqualTo(racingGame.getRacingCarList().size());

        // RacingHistory 체크
        assertThat(racingGame.getRacingHistoryList()).asList()
                .hasSize(userInputTotalTryCount)
                .map(o -> ((RacingHistory)o).getHistoryRacingCarList()).asList()
                .doesNotContainAnyElementsOf(userInputRacingCarList);

        // 우승자 1명 이상 체크
        assertThat(racingGame.findWinners())
                .asList()
                .hasSizeBetween(1, racingGame.getRacingCarList().size())
                .hasOnlyElementsOfType(RacingCar.class);

        // 우승자 동점 체크
        assertThat(
                racingGame.findWinners().stream()
                        .map(RacingCar::getScore)
                        .collect(Collectors.toSet())
                        .size()
        ).isEqualTo(1);
    }

    private void readUserInputCarNames(String carNames) {
        userInputRacingCarList = RacingCar.of(carNames.split(GameConfig.getDefault().getMessage(RacingGameMessage.RACING_CARS_INPUT_DELIMITER)));

        userInput(carNames);
    }

    private void readUserInputTotalTryCount() {
        readUserInputTotalTryCount(new Random().nextInt(MAX_TOTAL_TRY_COUNT) + 1);
    }

    private void readUserInputTotalTryCount(int totalTryCount) {
        userInputTotalTryCount = totalTryCount;

        userInput(totalTryCount);
    }

    private void userInput(String input) {
        stringJoiner.add(input);
    }
    private void userInput(int input) {
        userInput(String.valueOf(input));
    }

    private InputStream getUserInputStream() {
        return new ByteArrayInputStream(stringJoiner.toString().getBytes());
    }
}