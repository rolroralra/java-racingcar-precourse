package game.utils;

import java.util.Random;

public class DefaultRandomNumberGenerator implements RandomNumberGenerator {
    private static final Random RANDOM = new Random();

    @Override
    public int generateRandomNumber() {
        return RANDOM.nextInt();
    }

}
