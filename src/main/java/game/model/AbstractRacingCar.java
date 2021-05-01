package game.model;

import game.utils.RandomNumberGenerator;
import lombok.Setter;

public class AbstractRacingCar {
    public static final int MAX_NAME_LENGTH = 5;
    public static final int MIN_VALID_NUMBER = 0;
    public static final int MAX_VALID_NUMBER = 9;
    public static final int MIN_SUCCESS_NUMBER = 4;
    public static final int MAX_SUCCESS_NUMBER = 9;
    protected static final int SUCCESS_NUMBER_RANGE = MAX_SUCCESS_NUMBER - MIN_SUCCESS_NUMBER + 1;
    protected static final int VALID_NUMBER_RANGE = MAX_VALID_NUMBER - MIN_VALID_NUMBER + 1;

    @Setter
    protected RandomNumberGenerator rng;

    public AbstractRacingCar() {
        this(RandomNumberGenerator.getDefault());
    }

    public AbstractRacingCar(RandomNumberGenerator rng) {
        this.rng = rng;
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

    public static int convertToSuccessNumber(int number) {
        return ((number % SUCCESS_NUMBER_RANGE) + SUCCESS_NUMBER_RANGE) % SUCCESS_NUMBER_RANGE + MIN_SUCCESS_NUMBER;
    }

    protected int nextInt() {
        return convertToValidNumber(rng.nextInt());
    }
}
