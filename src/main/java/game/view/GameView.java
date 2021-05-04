package game.view;

import game.config.GameConfig;
import game.exception.GameException;

import java.io.IOException;

public class GameView {
    protected final GameConfig config;

    public GameView(GameConfig config) {
        this.config = config;
    }

    private void _print(String inputString) {
        try {
            config.getOutputStream().write(inputString.getBytes());
        } catch (IOException e) {
            throw new GameException(e);
        }
    }

    private void _println(String inputString) {
        _print(inputString + "\n");
    }

    public void println() {
        _print("\n");
    }

    public void println(Object object) {
        _println(object.toString());
    }

    public void println(Exception e) {
        _println(e.getMessage());
    }

    public void println(GameException e){
        _println(String.format(config.getMessage(e.getMessageKey()), e.getArguments()));
    }
}
