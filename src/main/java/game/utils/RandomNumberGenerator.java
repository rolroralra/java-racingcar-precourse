package game.utils;

public interface RandomNumberGenerator {
    int generateRandomNumber();

    default int nextInt() {
        return generateRandomNumber();
    }

    static RandomNumberGenerator getDefault() {
        return new DefaultRandomNumberGenerator();
    }
}
