package game.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RacingGameTest {
    private RacingCar[] racingCarArray;
    private RacingGame racingGame;
    private int totalTryCount;

    @BeforeEach
    void setUp() {
        totalTryCount = 10;

        racingCarArray = new RacingCar[] {
            new RacingCar("bmw"),
            new RacingCar("benz"),
            new RacingCar("쏘나타"),
            new RacingCar("audi")
        };

        racingGame = new RacingGame(totalTryCount, racingCarArray);
    }

    @DisplayName("레이싱_게임_단위_진행_테스트")
    @Test
    void 레이싱_게임_단위_진행_테스트() {
        for (int i = 0; i < totalTryCount; i++) {
            racingGame.nextStep();
        }

        // TODO: Exception Handling for already completed RacingGame
        racingGame.nextStep();
        racingGame.nextStep();
        racingGame.nextStep(3);
        racingGame.nextAllStep();

        assertRacingGame(racingGame);

        printRacingGame(racingGame);
    }

    @DisplayName("레이싱_게임_다중_단위_진행_테스트")
    @Test
    void 레이싱_게임_다중_단위_진행_테스트() {
        racingGame.nextStep(totalTryCount);

        // TODO: Exception Handling for already completed RacingGame
        racingGame.nextStep();
        racingGame.nextStep();
        racingGame.nextStep(3);
        racingGame.nextAllStep();

        assertRacingGame(racingGame);

        printRacingGame(racingGame);

    }

    @DisplayName("레이싱_게임_자동_진행_테스트")
    @Test
    void 레이싱_게임_자동_진행_테스트() {
        racingGame.nextAllStep();

        // TODO: Exception Handling for already completed RacingGame
        racingGame.nextStep();
        racingGame.nextStep();
        racingGame.nextStep(3);
        racingGame.nextAllStep();

        assertRacingGame(racingGame);

        printRacingGame(racingGame);
    }

    private void printRacingGame(RacingGame racingGame) {
        System.out.println(racingGame);
        System.out.println(racingGame.getResultString());
    }

    private void assertRacingGame(RacingGame racingGame) {
        assertThat(racingGame.getTryCount()).isEqualTo(totalTryCount);
        assertThat(racingGame.getTotalTryCount()).isEqualTo(totalTryCount);
        assertThat(racingGame.getCarList()).asList().hasSameSizeAs(racingCarArray);
        assertThat(racingGame.toString().split("\n").length).isEqualTo(racingGame.getCarList().size());
        assertThat(racingGame.findWinners()).asList()
                .hasSizeBetween(1, racingGame.getCarList().size())
                .hasOnlyElementsOfType(RacingCar.class);
        assertThat(racingGame.findWinners().stream().map(RacingCar::getScore).collect(Collectors.toSet()).size())
                .isEqualTo(1);
    }
}
