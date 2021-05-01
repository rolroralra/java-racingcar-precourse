package game.controller;

import game.config.GameConfig;
import game.model.RacingCar;
import game.model.RacingGame;
import game.view.RacingGameView;
import lombok.Getter;

import java.util.List;

public class RacingGameController extends GameTemplate {
    @Getter
    private RacingGame racingGame;
    private RacingGameView view;

    public RacingGameController(GameConfig config) {
        super(config);
        this.view = new RacingGameView(config);
    }

    @Override
    protected void init() {
        view.printRacingCarsInputPrompt();

        List<RacingCar> racingCarList = RacingCar.of(readRacingCarNames());

        view.printTotalTryCountInputPrompt();

        int totalTryCount = readTotalTryCount();

        racingGame = new RacingGame(totalTryCount, racingCarList);

        view.printNewLine();
    }

    private int readTotalTryCount() {
        return Integer.parseInt(readLine().trim());
    }

    private String[] readRacingCarNames() {
        return readLine().split(view.RACING_CARS_INPUT_DELIMITER);
    }

    @Override
    protected void process() {
        racingGame.nextStep();
    }

    @Override
    protected boolean isCompleted() {
        return racingGame.isCompleted();
    }

    @Override
    protected void destroy() {
        view.printRacingGameResultPrompt();
        view.printRacingGameHistory(racingGame);
        view.printNewLine();
        view.printRacingGameResult(racingGame);
    }

    @Override
    protected void handleException(Exception e) {
        view.print(e);
    }
}