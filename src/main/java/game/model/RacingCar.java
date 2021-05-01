package game.model;

import lombok.Getter;

@Getter
public class RacingCar extends AbstractRacingCar implements Comparable<RacingCar> {
    private final String name;
    private int score;

    public RacingCar(String name) {
        if (!isValidName(name)) {
            throw new RuntimeException(String.format("Car name's length should be less or equal to %d", MAX_NAME_LENGTH));
        }
        this.name = name;
        this.score = 0;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(String.format("%-" + MAX_NAME_LENGTH + "s", name));
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
