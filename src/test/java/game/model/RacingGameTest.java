package game.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RacingGameTest {
    private RacingGame racingGame;

    @BeforeEach
    void setUp() {
        racingGame = new RacingGame(
                new RacingCar("test1"),
                new RacingCar("test2"),
                new RacingCar("test3")
        );
    }


    @DisplayName("레이싱_게임_진행_테스트")
    @ParameterizedTest
    @ValueSource(ints = {10})
    void 레이싱_게임_진행_테스트(int tryCount) {

        for (int i = 0; i < tryCount; i++) {
            racingGame.nextStep();
        }

        System.out.println(racingGame);

        assertThat(racingGame.getTryCount()).isEqualTo(tryCount);
        assertThat(racingGame.toString().split("\n").length).isEqualTo(4);
        assertThat(racingGame.findWinners()).asList()
                .hasSizeBetween(1, racingGame.getCarList().size())
                .hasOnlyElementsOfType(RacingCar.class);
        assertThat(racingGame.findWinners().stream().map(RacingCar::getScore).collect(Collectors.toSet()).size())
                .isEqualTo(1);

        System.out.println(racingGame.getResultString());

    }

}
