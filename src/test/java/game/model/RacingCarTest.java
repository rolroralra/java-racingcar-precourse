package game.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RacingCarTest {

    @DisplayName("랜덤_숫자_0_9_검증")
    @Test
    void 랜덤_숫자_0_9_검증() {
        assertThat(RacingCar.checkValid(-1)).isFalse();
        assertThat(RacingCar.checkValid(0)).isTrue();
        assertThat(RacingCar.checkValid(1)).isTrue();
        assertThat(RacingCar.checkValid(2)).isTrue();
        assertThat(RacingCar.checkValid(3)).isTrue();
        assertThat(RacingCar.checkValid(4)).isTrue();
        assertThat(RacingCar.checkValid(5)).isTrue();
        assertThat(RacingCar.checkValid(6)).isTrue();
        assertThat(RacingCar.checkValid(7)).isTrue();
        assertThat(RacingCar.checkValid(8)).isTrue();
        assertThat(RacingCar.checkValid(9)).isTrue();
        assertThat(RacingCar.checkValid(10)).isFalse();
        assertThat(RacingCar.checkValid(11)).isFalse();
        assertThat(RacingCar.checkValid(12)).isFalse();
        assertThat(RacingCar.checkValid(13)).isFalse();
    }

    @DisplayName("랜덤_숫자_4_9_전진_조건_검증")
    @Test
    void 랜덤_숫자_4_9_전진_조건_검증() {
        assertThat(RacingCar.checkSuccess(-1)).isFalse();
        assertThat(RacingCar.checkSuccess(0)).isFalse();
        assertThat(RacingCar.checkSuccess(1)).isFalse();
        assertThat(RacingCar.checkSuccess(2)).isFalse();
        assertThat(RacingCar.checkSuccess(3)).isFalse();
        assertThat(RacingCar.checkSuccess(4)).isTrue();
        assertThat(RacingCar.checkSuccess(5)).isTrue();
        assertThat(RacingCar.checkSuccess(6)).isTrue();
        assertThat(RacingCar.checkSuccess(7)).isTrue();
        assertThat(RacingCar.checkSuccess(8)).isTrue();
        assertThat(RacingCar.checkSuccess(9)).isTrue();
        assertThat(RacingCar.checkSuccess(10)).isFalse();
        assertThat(RacingCar.checkSuccess(11)).isFalse();
        assertThat(RacingCar.checkSuccess(12)).isFalse();
        assertThat(RacingCar.checkSuccess(13)).isFalse();
    }

    @DisplayName("랜덤_숫자_4_9_전진_조건_검증")
    @Test
    void 랜덤_숫자_4_9_전진_조건_충족_숫자_산출_테스트() {
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(-1))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(0))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(1))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(2))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(3))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(4))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(5))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(6))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(7))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(8))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(9))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(10))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(11))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(12))).isTrue();
        assertThat(RacingCar.checkSuccess(RacingCar.toSuccess(13))).isTrue();
    }

    @DisplayName("레이싱카_생성자_테스트")
    @ValueSource(strings = {"bmw", "benz", "porsche", "audi"})
    void 레이싱카_생성자_테스트(String carName) {
        RacingCar car = new RacingCar(carName);
        assertThat(car.getName()).isEqualTo(carName);
        assertThat(car.getScore()).isEqualTo(0);
    }


    @DisplayName("레이싱카_전진_테스트")
    @ValueSource(strings = {"bmw", "benz", "porsche", "audi"})
    void 레이싱카_전진_테스트(String carName) {
        RacingCar car = new RacingCar(carName);
        for (int i = 0 ; i < 10; i++) {
            car.tryToGo();
        }

        System.out.println(car);
        assertThat(car.getName()).isEqualTo(carName);
        assertThat(car.getScore()).isGreaterThanOrEqualTo(0);
    }

}
