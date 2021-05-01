package game.model;

import game.constants.RacingGameMessage;
import game.exception.GameException;
import lombok.Getter;

import java.util.*;

@Getter
public class RacingGame {
    private final List<RacingCar> racingCarList;
    private final List<RacingHistory> racingHistoryList;
    private final int totalTryCount;
    private int tryCount;

    public RacingGame(int totalTryCount, RacingCar ...cars) {
        this(totalTryCount, Arrays.asList(cars));
    }

    public RacingGame(int totalTryCount, List<RacingCar> racingCarList) {
        if (!checkTotalTryCount(totalTryCount) || !checkRacingCarList(racingCarList)) {
            throw new GameException(RacingGameMessage.RACING_GAME_INVALID_EXCEPTION_FORMAT);
        }

        this.racingCarList = racingCarList;
        this.tryCount = 0;
        this.totalTryCount = totalTryCount;
        this.racingHistoryList = new ArrayList<>(totalTryCount);
    }

    public static boolean checkTotalTryCount(int totalTryCount) {
        return totalTryCount > 0;
    }

    public static boolean checkRacingCarList(List<RacingCar> racingCarList) {
        return racingCarList != null &&
                !racingCarList.isEmpty() &&
                racingCarList.stream().noneMatch(car -> car.getScore() != 0);
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

        for (RacingCar racingCar : racingCarList) {
            racingCar.tryToGo();
        }

        tryCount++;
        addHistory();
    }

    public boolean isCompleted() {
        return this.totalTryCount <= this.tryCount;
    }

    public void addHistory() {
        racingHistoryList.add(new RacingHistory(racingCarList));
    }

    public List<RacingCar> findWinners() {
        RacingCar maxCar = Collections.max(racingCarList);

        List<RacingCar> winners = new ArrayList<>();
        for (RacingCar car : racingCarList) {
            if (car.equalScore(maxCar)) {
                winners.add(car);
            }
        }

        return winners;
    }

    public String getHistoryString() {
        StringJoiner sj = new StringJoiner("\n\n");

        for (RacingHistory racingHistory: racingHistoryList) {
            sj.add(racingHistory.toString());
        }

        return sj.toString();
    }

    public String getResultString() {
        return getResultString(",", "", "");
    }

    public String getResultString(String delimiter, String prefix, String suffix) {
        if (!isCompleted()) {
            throw new GameException(RacingGameMessage.RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT, this.tryCount, this.totalTryCount);
        }

        StringJoiner sj = new StringJoiner(delimiter, prefix, suffix);

        for (RacingCar car : findWinners()) {
            sj.add(car.getName());
        }

        return sj.toString();
    }

    public String description() {
        return "totalTryCount=" + totalTryCount +
                ", tryCount=" + tryCount +
                ", carList=" +
                racingCarList.stream().map(RacingCar::description);
    }

    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner("\n");
        for (RacingCar car : racingCarList) {
            sj.add(car.toString());
        }

        return sj.toString();
    }
}
