package game.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum RacingGameMessage {
    RACING_CARS_INPUT_DELIMITER(
            MessageType.CONSTANT,
            "RACING_CARS_INPUT_DELIMITER",
            ","
    ),
    RACING_CARS_INPUT_DELIMITER_NAME(
            MessageType.CONSTANT,
            "RACING_CARS_INPUT_DELIMITER_NAME",
            "쉼표"
    ),
    RACING_CARS_INPUT_PROMPT_FORMAT(
            MessageType.PROMPT,
            "RACING_CARS_INPUT_PROMPT_FORMAT",
            "경주할 자동차 이름을 입력하세요.(이름은 %s(%s) 기준으로 구분)",
            String.class, String.class
    ),
    TOTAL_TRY_COUNT_INPUT_PROMPT(
            MessageType.PROMPT,
            "TOTAL_TRY_COUNT_INPUT_PROMPT",
            "시도할 회수는 몇회인가요?"
    ),
    RACING_GAME_RESULT_PROMPT(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_PROMPT",
            "실행결과:"
    ),
    RACING_GAME_RESULT_DELIMITER(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_DELIMITER",
            ","
    ),
    RACING_GAME_RESULT_PREFIX(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_PREFIX",
            ""
    ),
    RACING_GAME_RESULT_SUFFIX(
            MessageType.PROMPT,
            "RACING_GAME_RESULT_SUFFIX",
            "가 최종 우승했습니다."
    ),
    EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "EXCEPTION_FORMAT",
            "Exception 발생 (%s)",
            String.class
    ),

    RACING_CAR_INVALID_NAME_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "RACING_CAR_INVALID_NAME_EXCEPTION_FORMAT",
            "자동차 이름의 길이는 %d보다 같거나 작아야 합니다. (%s)",
            Integer.class, String.class
    ),
    RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT",
            "아직 종료되지 않았습니다. (Total: %d, Current: %d)",
            Integer.class, Integer.class
    ),
    RACING_GAME_INVALID_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "RACING_GAME_INVALID_EXCEPTION_FORMAT",
            "레이싱 게임 초기화에 문제가 발생하였습니다."
    ),
    GAME_INTERNAL_EXCEPTION_FORMAT(
            MessageType.EXCEPTION,
            "GAME_INTERNAL_EXCEPTION_FORMAT",
            "게임 내부 문제가 발생하였습니다. [%s]",
            String.class
    );

    public enum MessageType {
        CONSTANT, PROMPT, EXCEPTION;
    }

    private final MessageType type;
    private final String key;
    private final String sampleMessage;
    private final Class<?>[] argumentsClass;

    RacingGameMessage(MessageType type, String key, String sampleMessage, Class<?>... argumentsClass) {
        this.type = type;
        this.key = key;
        this.sampleMessage = sampleMessage;
        this.argumentsClass = argumentsClass;
    }

    public static List<RacingGameMessage> getAllMessage(MessageType type) {
        return Arrays.stream(values())
                .filter(message -> message.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageKey{");
        sb.append("type='").append(type).append(('\''));
        sb.append(", key='").append(key).append('\'');
        sb.append(", sampleMessage='").append(sampleMessage).append('\'');
        sb.append(", argumentsClass=").append(Arrays.toString(argumentsClass));
        sb.append('}');
        return sb.toString();
    }
}
