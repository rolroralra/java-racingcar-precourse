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

    protected void println(String inputString) {
        _print(inputString + "\n");
    }

    public void print(Object object) {
        println(object.toString());
    }

    public void print(Exception e) {
        println(e.getMessage());
    }

    public void print(GameException e){
        println(String.format(config.getMessage(e.getRacingGameMessage()), e.getArguments()));
    }

    public void printNewLine() {
        _print("\n");
    }
}
