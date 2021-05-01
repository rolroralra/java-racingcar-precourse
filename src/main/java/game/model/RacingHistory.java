package game.model;

import game.exception.GameException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
public class RacingHistory {
    private final List<RacingCar> historyRacingCarList;

    public RacingHistory(List<RacingCar> racingCarList) {
        this.historyRacingCarList = new ArrayList<>();

        for (RacingCar racingCar : racingCarList) {
            addHistoryCar(racingCar);
        }
    }

    private void addHistoryCar(RacingCar racingCar) {
        try {
            this.historyRacingCarList.add((RacingCar) racingCar.clone());
        } catch (CloneNotSupportedException e) {
            throw new GameException(e);
        }
    }

    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner("\n");
        for (RacingCar car : historyRacingCarList) {
            sj.add(car.toString());
        }

        return sj.toString();
    }
}
