package game.view;

import game.config.GameConfig;
import game.constants.RacingGameMessage;
import game.model.RacingGame;

import static game.constants.RacingGameMessage.*;

public class RacingGameView extends GameView {
    public final String RACING_CARS_INPUT_DELIMITER;
    public final String RACING_CARS_INPUT_DELIMITER_NAME;

    public RacingGameView(GameConfig config) {
        super(config);

        this.RACING_CARS_INPUT_DELIMITER = config.getMessage(RacingGameMessage.RACING_CARS_INPUT_DELIMITER);
        this.RACING_CARS_INPUT_DELIMITER_NAME = config.getMessage(RacingGameMessage.RACING_CARS_INPUT_DELIMITER_NAME);
    }

    public void printRacingCarsInputPrompt() {
        println(
                String.format(
                        config.getMessage(RACING_CARS_INPUT_PROMPT_FORMAT),
                        RACING_CARS_INPUT_DELIMITER_NAME,
                        RACING_CARS_INPUT_DELIMITER
                )
        );
    }

    public void printTotalTryCountInputPrompt() {
        println(
                config.getMessage(TOTAL_TRY_COUNT_INPUT_PROMPT)
        );
    }

    public void printRacingGameResultPrompt() {
        println(
                config.getMessage(RACING_GAME_RESULT_PROMPT)
        );
    }

    public void printRacingGameResult(RacingGame racingGame) {
        println(
                racingGame.getResultString(
                        config.getMessage(RACING_GAME_RESULT_DELIMITER),
                        config.getMessage(RACING_GAME_RESULT_PREFIX),
                        config.getMessage(RACING_GAME_RESULT_SUFFIX)
                )
        );
    }


    public void printRacingGameHistory(RacingGame racingGame) {
        println(
                racingGame.getHistoryString()
        );
    }
}
