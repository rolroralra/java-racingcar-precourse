package game.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static game.model.RacingCar.MAX_NAME_LENGTH;
import static org.assertj.core.api.Assertions.*;

public class RacingCarTest {

    @DisplayName("랜덤_숫자_0_9_검증")
    @Test
    void 랜덤_숫자_0_9_검증() {
        assertThat(RacingCar.checkValidToGo(-1)).isFalse();
        assertThat(RacingCar.checkValidToGo(0)).isTrue();
        assertThat(RacingCar.checkValidToGo(1)).isTrue();
        assertThat(RacingCar.checkValidToGo(2)).isTrue();
        assertThat(RacingCar.checkValidToGo(3)).isTrue();
        assertThat(RacingCar.checkValidToGo(4)).isTrue();
        assertThat(RacingCar.checkValidToGo(5)).isTrue();
        assertThat(RacingCar.checkValidToGo(6)).isTrue();
        assertThat(RacingCar.checkValidToGo(7)).isTrue();
        assertThat(RacingCar.checkValidToGo(8)).isTrue();
        assertThat(RacingCar.checkValidToGo(9)).isTrue();
        assertThat(RacingCar.checkValidToGo(10)).isFalse();
        assertThat(RacingCar.checkValidToGo(11)).isFalse();
        assertThat(RacingCar.checkValidToGo(12)).isFalse();
        assertThat(RacingCar.checkValidToGo(13)).isFalse();
    }

    @DisplayName("랜덤_숫자_4_9_전진_조건_검증")
    @Test
    void 랜덤_숫자_4_9_전진_조건_검증() {
        assertThat(RacingCar.checkSuccessToGo(-1)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(0)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(1)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(2)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(3)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(4)).isTrue();
        assertThat(RacingCar.checkSuccessToGo(5)).isTrue();
        assertThat(RacingCar.checkSuccessToGo(6)).isTrue();
        assertThat(RacingCar.checkSuccessToGo(7)).isTrue();
        assertThat(RacingCar.checkSuccessToGo(8)).isTrue();
        assertThat(RacingCar.checkSuccessToGo(9)).isTrue();
        assertThat(RacingCar.checkSuccessToGo(10)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(11)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(12)).isFalse();
        assertThat(RacingCar.checkSuccessToGo(13)).isFalse();
    }

    @DisplayName("랜덤_숫자_4_9_전진_조건_검증")
    @Test
    void 랜덤_숫자_4_9_전진_조건_충족_숫자_산출_테스트() {
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(-1))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(0))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(1))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(2))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(3))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(4))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(5))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(6))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(7))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(8))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(9))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(10))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(11))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(12))).isTrue();
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumer(13))).isTrue();
    }

    @DisplayName("레이싱카_생성자_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"bmw", "benz", "쏘나타", "audi"})
    void 레이싱카_생성자_테스트(String carName) {
        RacingCar car = new RacingCar(carName);
        assertThat(car.getName()).isEqualTo(carName);
        assertThat(car.getScore()).isEqualTo(0);

        System.out.println(car);
    }

    @DisplayName("레이싱카_이름_5자_초과_예외_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"porsche", "Lamborghini", "chevrolet", "Ferrari"})
    void 레이싱카_이름_5자_초과_예외_테스트(String carName) {
        // TODO: Exception Class or Exception Message
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> new RacingCar(carName)).withMessage(String.format("Car name's length should be less or equal to %d", MAX_NAME_LENGTH));
    }


    @DisplayName("레이싱카_전진_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"bmw", "benz", "쏘나타", "audi"})
    void 레이싱카_전진_테스트(String carName) {
        RacingCar car = new RacingCar(carName);
        for (int i = 0 ; i < 10; i++) {
            car.tryToGo();
        }

        assertThat(car.getName()).isEqualTo(carName);
        assertThat(car.getScore()).isGreaterThanOrEqualTo(0);

        System.out.println(car);
    }

}
