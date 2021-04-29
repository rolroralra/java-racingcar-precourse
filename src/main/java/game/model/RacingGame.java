package game.model;

import lombok.Getter;

import java.util.*;

@Getter
public class RacingGame {
    private List<RacingCar> carList;
    private int tryCount;

    public RacingGame(RacingCar ...cars) {
        this(Arrays.asList(cars));
    }

    public RacingGame(List<RacingCar> carList) {
        this.carList = carList;
        this.tryCount = 0;
    }

    public void nextStep() {
        for (RacingCar car : carList) {
            car.tryToGo();
        }

        tryCount++;
    }

    public List<RacingCar> findWinners() {
        RacingCar maxCar = Collections.max(carList);

        List<RacingCar> winners = new ArrayList<>();
        for (RacingCar car : carList) {
            if (car.equalScore(maxCar)) {
                winners.add(car);
            }
        }

        return winners;
    }

    public String getResultString() {
        StringJoiner sj = new StringJoiner(", ", "", "가 최종 우승했습니다.");

        for (RacingCar car : findWinners()) {
            sj.add(car.getName());
        }

        return sj.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("실행결과");
        for (RacingCar car : carList) {
            sb.append("\n").append(car.toString());
        }

        return sb.toString();
    }
}
