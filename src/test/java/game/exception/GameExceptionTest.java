package game.exception;

import game.constants.RacingGameMessage;
import game.model.RacingCar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GameExceptionTest {
    @DisplayName("예외_생성자_예외_케이스_테스트")
    @Test
    void 예외_생성자_예외_케이스_테스트() {
        RacingGameMessage.getAllMessage(RacingGameMessage.MessageType.EXCEPTION).forEach(gameMessage ->
            assertThatExceptionOfType(GameException.class).isThrownBy(() -> {
                GameException e = new GameException(gameMessage, new Object());
                e.printStackTrace();
            })
        );
    }

    @DisplayName("예외_생성자_정상_테스트")
    @Test
    void 예외_생성자_정상_테스트() {
        System.out.println(new GameException("에러 메시지").getMessage());
        System.out.println(new GameException(new RuntimeException("런타임 에러")).getMessage());
        System.out.println(new GameException(RacingGameMessage.GAME_INTERNAL_EXCEPTION_FORMAT, "내부 서버 문제").getMessage());
        System.out.println(new GameException(RacingGameMessage.EXCEPTION_FORMAT, "내부 예외 발생").getMessage());
        System.out.println(new GameException(RacingGameMessage.RACING_GAME_INVALID_EXCEPTION_FORMAT).getMessage());
        System.out.println(new GameException(RacingGameMessage.RACING_GAME_NOT_COMPLETED_EXCEPTION_FORMAT, 10, 9).getMessage());
        System.out.println(new GameException(RacingGameMessage.RACING_CAR_INVALID_NAME_EXCEPTION_FORMAT, RacingCar.MAX_NAME_LENGTH, "이름이긴자동차이름").getMessage());
    }
}
