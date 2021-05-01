package game.model;

import game.constants.RacingGameMessage;
import game.exception.GameException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RacingCar extends AbstractRacingCar implements Comparable<RacingCar>, Cloneable {
    private final String name;
    private int score;

    public RacingCar(String name) {
        if (!isValidName(name)) {
            throw new GameException(RacingGameMessage.RACING_CAR_INVALID_NAME_EXCEPTION_FORMAT, RacingCar.MAX_NAME_LENGTH, name);
        }

        this.name = name;
        this.score = 0;
    }

    public static RacingCar of(String name) {
        return new RacingCar(name);
    }

    public static List<RacingCar> of(String... names) {
        return Arrays.stream(names)
                .map(RacingCar::of)
                .collect(Collectors.toList());
    }

    public boolean tryToGo(int number) {
        if (checkSuccessToGo(number)) {
            this.score++;
            return true;
        }

        return false;
    }

    public boolean tryToGo() {
        return tryToGo(nextInt());
    }

    private static boolean isValidName(String name) {
        return name != null && name.length() <= MAX_NAME_LENGTH;
    }

    public boolean equalScore(RacingCar car) {
        return this.score == car.getScore();
    }

    public String description() {
        final StringBuilder sb = new StringBuilder("RacingCar{");
        sb.append("name='").append(name).append('\'');
        sb.append(", score=").append(score);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(RacingCar o) {
        return this.score - o.score;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.format("%-" + MAX_NAME_LENGTH + "s", name));
        sb.append(":");
        for (int i = 0; i < score; i++) {
            sb.append("-");
        }

        return sb.toString();
    }
}
