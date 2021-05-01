package game.controller;

import game.config.GameConfig;
import game.constants.RacingGameMessage;
import game.model.RacingCar;
import game.model.RacingGame;
import game.model.RacingHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameControllerTest {
    private static int MAX_TOTAL_TRY_COUNT = 20;
    private GameConfig config;
    private RacingGameController game;
    private int totalTryCount;
    private List<RacingCar> racingCarList;


    @DisplayName("컨트롤러_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    public void 컨트롤러_테스트(String language) {
        String sampleInput = "bmw,audi,쏘나타,test";

        racingCarList = RacingCar.of(sampleInput.split(GameConfig.getDefault().getMessage(RacingGameMessage.RACING_CARS_INPUT_DELIMITER)));

        StringJoiner sj = new StringJoiner("\n", "", "\n");
        sj.add(sampleInput);

        totalTryCount = new Random().nextInt(MAX_TOTAL_TRY_COUNT) + 1;
        sj.add(String.valueOf(totalTryCount));

        ByteArrayInputStream baio = new ByteArrayInputStream(sj.toString().getBytes());

        init(language, baio, System.out);

        game.run();

        assertCompletedRacingGame(game.getRacingGame());
    }

    private void init(String language, InputStream inputStream, OutputStream outputStream) {
        config = GameConfig.builder()
                .locale(language)
                .inputStream(inputStream)
                .outputStream(outputStream)
                .build();
        game = new RacingGameController(config);
    }

    private void assertCompletedRacingGame(RacingGame racingGame) {
        // RacingGame 기본 정보 체크
        assertThat(racingGame.getTryCount()).isEqualTo(totalTryCount);
        assertThat(racingGame.getTotalTryCount()).isEqualTo(totalTryCount);
        assertThat(racingGame.getRacingCarList()).asList().hasSameSizeAs(racingCarList);
        assertThat(racingGame.toString().split("\n").length).isEqualTo(racingGame.getRacingCarList().size());

        // RacingHistory 체크
        assertThat(racingGame.getRacingHistoryList()).asList()
                .hasSize(totalTryCount)
                .map(o -> ((RacingHistory)o).getHistoryRacingCarList()).asList()
                .doesNotContainAnyElementsOf(racingCarList);

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
}