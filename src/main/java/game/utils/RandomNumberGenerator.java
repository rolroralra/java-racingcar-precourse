package game.utils;

import game.model.RacingCar;

import java.util.Random;

public class RandomNumberGenerator {
    private static final Random RANDOM = new Random();

    public static int nextInt() {
        return RacingCar.toValid(RANDOM.nextInt());
    }
}
