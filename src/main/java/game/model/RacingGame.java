package game.model;

import game.constants.RacingGameMessage;
import game.exception.GameException;
import game.utils.StringJoinUtil;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

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

    private List<String> getWinnerNameList() {
        return findWinners().stream()
                .map(RacingCar::getName)
                .collect(Collectors.toList());
    }

    public String getHistoryString() {
        return StringJoinUtil.join(racingHistoryList, "\n\n");
    }

    public String getResultString() {
        return getResultString(",", "", "");
    }

    public String getResultString(String delimiter, String prefix, String suffix) {
        if (!isCompleted()) {
            throw new GameException(RacingGameMessage.RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT, this.tryCount, this.totalTryCount);
        }

        return StringJoinUtil.join(getWinnerNameList(), delimiter, prefix, suffix);
    }

    public String description() {
        return "totalTryCount=" + totalTryCount +
                ", tryCount=" + tryCount +
                ", carList=" +
                racingCarList.stream().map(RacingCar::description);
    }

    @Override
    public String toString() {
        return StringJoinUtil.join(racingCarList, "\n");
    }
}
