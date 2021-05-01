package game.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static game.model.RacingCar.MAX_NAME_LENGTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RacingCarTest {
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
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> new RacingCar(carName))
                .withMessage("Car name's length should be less or equal to %d", MAX_NAME_LENGTH);
    }

    @DisplayName("랜덤_숫자_0_9_유효성_검증_성공_테스트")
    @ParameterizedTest
    @MethodSource(value = {
            "game.TestProvider#provideValidRandomNumbers"
    })
    void 랜덤_숫자_0_9_유효성_검증_성공_테스트(int number) {
        assertThat(RacingCar.checkValidToGo(number)).isTrue();
    }

    @DisplayName("랜덤_숫자_0_9_유효성_검증_실패_테스트")
    @ParameterizedTest
    @MethodSource(value = {
            "game.TestProvider#provideInValidRandomNumbers"
    })
    void 랜덤_숫자_0_9_유효성_검증_실패_테스트(int number) {
        assertThat(RacingCar.checkValidToGo(number)).isFalse();
    }

    @DisplayName("랜덤_숫자_4_9_전진_조건_검증_성공_테스트")
    @ParameterizedTest
    @MethodSource(value = {"game.TestProvider#provideSuccessRandomNumbers"})
    void 랜덤_숫자_4_9_전진_조건_검증_성공_테스트(int number) {
        assertThat(RacingCar.checkSuccessToGo(number)).isTrue();
    }

    @DisplayName("랜덤_숫자_4_9_전진_조건_검증_실패_테스트")
    @ParameterizedTest
    @MethodSource(value = {
            "game.TestProvider#provideFailedRandomNumbers",
            "game.TestProvider#provideInValidRandomNumbers",
    })
    void 랜덤_숫자_4_9_전진_조건_검증_실패_테스트(int number) {
        assertThat(RacingCar.checkSuccessToGo(number)).isFalse();
    }

    @DisplayName("레이싱카_수동_입력값_전진_테스트")
    @ParameterizedTest
    @MethodSource(value = {
            "game.TestProvider#provideSuccessRandomNumbers"
    })
    void 레이싱카_수동_입력값_전진_테스트(int number) {
        RacingCar racingCar = new RacingCar("test");
        assertThat(racingCar.tryToGo(number)).isTrue();
        assertThat(racingCar.getScore()).isEqualTo(1);
    }

    @DisplayName("레이싱카_수동_입력값_정지_테스트")
    @ParameterizedTest
    @MethodSource(value = {
            "game.TestProvider#provideFailedRandomNumbers",
            "game.TestProvider#provideInValidRandomNumbers",
    })
    void 레이싱카_수동_입력값_정지_테스트(int number) {
        RacingCar racingCar = new RacingCar("test");
        assertThat(racingCar.tryToGo(number)).isFalse();
        assertThat(racingCar.getScore()).isEqualTo(0);
    }

    @DisplayName("레이싱카_전진_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"bmw", "benz", "쏘나타", "audi"})
    void 레이싱카_전진_테스트(String carName) {
        RacingCar car = new RacingCar(carName);
        for (int i = 0; i < 10; i++) {
            car.tryToGo();
        }

        assertThat(car.getName()).isEqualTo(carName);
        assertThat(car.getScore()).isGreaterThanOrEqualTo(0);

        System.out.println(car);
    }

    @DisplayName("랜덤_숫자_4_9_전진_조건_충족_숫자_산출_테스트")
    @ParameterizedTest
    @MethodSource(value = {
            "game.TestProvider#provideAllTestCaseRandomNumbers",
    })
    void 랜덤_숫자_4_9_전진_조건_충족_숫자_산출_테스트(int number) {
        assertThat(RacingCar.checkSuccessToGo(RacingCar.convertToSuccessNumber(number))).isTrue();
    }

    @DisplayName("랜덤_생성자_사용자_설정_레이싱카_전진_테스트")
    @Test
    void 랜덤_생성자_사용자_설정_레이싱카_전진_테스트() {
        RacingCar car = new RacingCar("test");
        car.setRng(() -> new Random().nextInt(20));

        for (int i = 0; i < 10; i++) {
            car.tryToGo();
        }

        assertThat(car.getScore()).isGreaterThanOrEqualTo(0);

        System.out.println(car);
    }



}
