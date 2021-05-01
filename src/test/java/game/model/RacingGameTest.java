package game.model;

import game.exception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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

    @DisplayName("레이싱_게임_생성자_정상_작동_테스트")
    @ParameterizedTest
    @MethodSource("game.TestProvider#providePositiveTotalTryCounts")
    public void 레이싱_게임_생성자_정상_작동_테스트(int totalTryCount) {
        racingGame = new RacingGame(totalTryCount, racingCarArray);
        this.totalTryCount = totalTryCount;

        racingGame.nextAllStep();

        assertCompletedRacingGame(racingGame);

        printRacingGame(racingGame);
    }

    @DisplayName("레이싱_게임_생성자_총_시도_회수_음수_값_예외_테스트")
    @ParameterizedTest
    @MethodSource("game.TestProvider#provideNonPositiveTotalTryCounts")
    public void 레이싱_게임_생성자_총_시도_회수_음수_값_예외_테스트(int totalTryCount) {
        assertThatExceptionOfType(GameException.class)
                .isThrownBy(() -> new RacingGame(totalTryCount, racingCarArray));
    }

    @DisplayName("레이싱_게임_생성자_레이싱카_0대_예외_테스트")
    @ParameterizedTest
    @MethodSource("game.TestProvider#providePositiveTotalTryCounts")
    public void 레이싱_게임_생성자_레이싱카_0대_예외_테스트(int totalTryCount) {
        assertThatExceptionOfType(GameException.class)
                .isThrownBy(() -> new RacingGame(totalTryCount));

        assertThatExceptionOfType(GameException.class)
                .isThrownBy(() -> new RacingGame(totalTryCount, new ArrayList<>()));
    }

    @DisplayName("레이싱_게임_단위_진행_테스트")
    @Test
    public void 레이싱_게임_단위_진행_테스트() {
        while(!racingGame.isCompleted()) {
            racingGame.nextStep();
        }

        // TODO: Exception Handling for already completed RacingGame
        racingGame.nextStep();
        racingGame.nextStep();
        racingGame.nextStep(3);
        racingGame.nextAllStep();

        assertCompletedRacingGame(racingGame);

        printRacingGame(racingGame);
    }

    @DisplayName("레이싱_게임_다중_단위_진행_테스트")
    @Test
    public void 레이싱_게임_다중_단위_진행_테스트() {
        racingGame.nextStep(totalTryCount);

        // TODO: Exception Handling for already completed RacingGame
        racingGame.nextStep();
        racingGame.nextStep();
        racingGame.nextStep(3);
        racingGame.nextAllStep();

        assertCompletedRacingGame(racingGame);

        printRacingGame(racingGame);
    }

    @DisplayName("레이싱_게임_자동_진행_테스트")
    @Test
    public void 레이싱_게임_자동_진행_테스트() {
        racingGame.nextAllStep();

        // TODO: Exception Handling for already completed RacingGame
        racingGame.nextStep();
        racingGame.nextStep();
        racingGame.nextStep(3);
        racingGame.nextAllStep();

        assertCompletedRacingGame(racingGame);

        printRacingGame(racingGame);
    }

    @DisplayName("레이싱_게임_미완료_테스트")
    @Test
    public void 레이싱_게임_미완료_테스트() {
        int tryCount = new Random().nextInt(totalTryCount);
        racingGame.nextStep(tryCount);

        assertThat(racingGame.isCompleted()).isFalse();
        assertThatExceptionOfType(GameException.class)
                .isThrownBy(() -> racingGame.getResultString());

        System.out.println(racingGame);
    }

    private void printRacingGame(RacingGame racingGame) {
        System.out.println(racingGame);
        System.out.println(racingGame.description());
        System.out.println(racingGame.getHistoryString());
        System.out.println(racingGame.getResultString());
    }

    private void assertCompletedRacingGame(RacingGame racingGame) {
        // RacingGame 기본 정보 체크
        assertThat(racingGame.getTryCount()).isEqualTo(totalTryCount);
        assertThat(racingGame.getTotalTryCount()).isEqualTo(totalTryCount);
        assertThat(racingGame.getRacingCarList()).asList().hasSameSizeAs(racingCarArray);
        assertThat(racingGame.toString().split("\n").length).isEqualTo(racingGame.getRacingCarList().size());

        // RacingHistory 체크
        assertThat(racingGame.getRacingHistoryList()).asList()
                .hasSize(totalTryCount)
                .map(o -> ((RacingHistory)o).getHistoryRacingCarList()).asList()
                .doesNotContainAnyElementsOf(Arrays.asList(racingCarArray));

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
