package game;

import game.model.RacingCar;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestProvider {

    public static final int MIN_TEST_NUMBER = -7;
    public static final int MAX_TEST_NUMBER = 14;

    public static List<Arguments> provideAllTestCaseRandomNumbers() {
        return provideIntRange(MIN_TEST_NUMBER, MAX_TEST_NUMBER);
    }

    public static List<Arguments> provideValidRandomNumbers() {
        return provideIntRange(RacingCar.MIN_VALID_NUMBER, RacingCar.MAX_VALID_NUMBER);
    }

    public static List<Arguments> provideInValidRandomNumbers() {
        return provideAllTestCaseRandomNumbers().stream()
                .filter(arg -> !RacingCar.checkValidToGo((int)arg.get()[0]))
                .collect(Collectors.toList());
    }

    public static List<Arguments> provideSuccessRandomNumbers() {
        return provideIntRange(RacingCar.MIN_SUCCESS_NUMBER, RacingCar.MAX_SUCCESS_NUMBER);
    }

    public static List<Arguments> provideFailedRandomNumbers() {
        return provideValidRandomNumbers().stream()
                .filter(arg -> !RacingCar.checkSuccessToGo((int)arg.get()[0]))
                .collect(Collectors.toList());
    }

    public static List<Arguments> providePositiveTotalTryCounts() {
        return provideIntRange(1, 100);
    }

    public static List<Arguments> provideNonPositiveTotalTryCounts() {
        return provideIntRange(MIN_TEST_NUMBER, 0);
    }

    private static List<Arguments> provideIntRange(int startInclusive, int endInclusive) {
        return IntStream.range(startInclusive, endInclusive + 1)
                .mapToObj(Arguments::of)
                .collect(Collectors.toList());
    }
}
