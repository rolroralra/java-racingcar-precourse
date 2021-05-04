package game.constants;

import lombok.Getter;

public enum GameMessage {
    RACING_CARS_INPUT_DELIMITER(
            MessageType.CONSTANT,
            "RACING_CARS_INPUT_DELIMITER"
    ),
    RACING_CARS_INPUT_DELIMITER_NAME(
            MessageType.CONSTANT,
            "RACING_CARS_INPUT_DELIMITER_NAME"
    ),
    RACING_CARS_INPUT_PROMPT_FORMAT(
            MessageType.PROMPT,
            "RACING_CARS_INPUT_PROMPT_FORMAT"
    ),
    TOTAL_TRY_COUNT_INPUT_PROMPT(
            MessageType.PROMPT,
            "TOTAL_TRY_COUNT_INPUT_PROMPT"
    ),
    RACING_GAME_RESULT_PROMPT(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_PROMPT"
    ),
    RACING_GAME_RESULT_DELIMITER(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_DELIMITER"
    ),
    RACING_GAME_RESULT_PREFIX(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_PREFIX"
    ),
    RACING_GAME_RESULT_SUFFIX(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_SUFFIX"
    ),
    RACING_CAR_INVALID_NAME_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "RACING_CAR_INVALID_NAME_EXCEPTION_FORMAT"
    ),
    RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT"
    ),
    RACING_GAME_INVALID_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "RACING_GAME_INVALID_EXCEPTION_FORMAT"
    ),
    GAME_INTERNAL_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "GAME_INTERNAL_EXCEPTION_FORMAT"
    );

    public enum MessageType {
        CONSTANT, PROMPT, EXCEPTION;
    }

    private final GameMessage.MessageType type;
    @Getter
    private final String key;

    GameMessage(GameMessage.MessageType type, String key) {
        this.type = type;
        this.key = key;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RacingGameConstants{");
        sb.append("type=").append(type);
        sb.append(", key='").append(key).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
