package game.model;

import lombok.Getter;

import java.util.*;

@Getter
public class RacingGame {
    private final List<RacingCar> carList;
    private final int totalTryCount;
    private int tryCount;

    public RacingGame(int totalTryCount, RacingCar ...cars) {
        this(totalTryCount, Arrays.asList(cars));
    }

    public RacingGame(int totalTryCount, List<RacingCar> carList) {
        this.carList = carList;
        this.tryCount = 0;
        this.totalTryCount = totalTryCount;
    }

    public void nextAllStep() {
        nextStep(this.totalTryCount - this.tryCount);
    }

    public void nextStep(int tryCount) {
        for (int i = 0; i < tryCount && !isCompleted(); i++) {
            nextStep();
        }
    }

    public void nextStep() {
        if (isCompleted()) {
            return;
        }

        for (RacingCar car : carList) {
            car.tryToGo();
        }

        tryCount++;
    }

    public boolean isCompleted() {
        return this.totalTryCount <= this.tryCount;
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
        // TODO: Add Exception Class And Message Bundle
        if (!isCompleted()) {
            throw new RuntimeException(String.format("아직 종료되지 않았습니다. Total: %d, Current: %d", totalTryCount, tryCount));
        }

        StringJoiner sj = new StringJoiner(", ", "", "가 최종 우승했습니다.");

        for (RacingCar car : findWinners()) {
            sj.add(car.getName());
        }

        return sj.toString();
    }

    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner("\n");
        for (RacingCar car : carList) {
            sj.add(car.toString());
        }

        return sj.toString();
    }
}
