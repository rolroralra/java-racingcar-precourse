package game.model;

import game.utils.RandomNumberGenerator;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RacingCar implements Comparable<RacingCar> {
    public static final int MAX_NAME_LENGTH = 5;
    public static final int MIN_VALID_NUMBER = 0;
    public static final int MAX_VALID_NUMBER = 9;
    public static final int MIN_SUCCESS_NUMBER = 4;
    public static final int MAX_SUCCESS_NUMBER = 9;
    private static final int SUCCESS_NUMBER_RANGE = MAX_SUCCESS_NUMBER - MIN_SUCCESS_NUMBER + 1;
    private static final int VALID_NUMBER_RANGE = MAX_VALID_NUMBER - MIN_VALID_NUMBER + 1;

    private final String name;
    private int score;

    public RacingCar(String name) {
        if (!isValidName(name)) {
            throw new RuntimeException(String.format("Car name's length should be less or equal to %d", MAX_NAME_LENGTH));
        }
        this.name = name;
        this.score = 0;
    }

    public static boolean checkValidToGo(int number) {
        return number >= MIN_VALID_NUMBER && number <= MAX_VALID_NUMBER;
    }

    public static boolean checkSuccessToGo(int number) {
        return number >= MIN_SUCCESS_NUMBER && number <= MAX_SUCCESS_NUMBER;
    }

    public static int convertToValidNumber(int number) {
        return ((number % VALID_NUMBER_RANGE) + VALID_NUMBER_RANGE) % VALID_NUMBER_RANGE + MIN_VALID_NUMBER;
    }

    public static int convertToSuccessNumer(int number) {
        return ((number % SUCCESS_NUMBER_RANGE) + SUCCESS_NUMBER_RANGE) % SUCCESS_NUMBER_RANGE + MIN_SUCCESS_NUMBER;
    }

    public void tryToGo() {
        // TODO: Strategy Pattern for segregation random generation interface
        if (checkSuccessToGo(RandomNumberGenerator.nextInt())) {
            this.score++;
        }
    }

    private static boolean isValidName(String name) {
        return name != null && name.length() <= MAX_NAME_LENGTH;
    }

    public boolean equalScore(RacingCar car) {
        return this.score == car.getScore();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(name);
        sb.append(":");
        for (int i = 0; i < score; i++) {
            sb.append("-");
        }

        return sb.toString();
    }

    @Override
    public int compareTo(RacingCar o) {
        return this.score - o.score;
    }
}
