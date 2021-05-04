package game;

import game.config.GameConfig;
import game.controller.GameTemplate;
import game.controller.RacingGameController;

public class GameMain {
    public static void main(String[] args) {
        GameConfig config = GameConfig.builder()
                .locale("ko")
                .build();
        GameTemplate game = new RacingGameController(config);
        game.run();
    }
}
