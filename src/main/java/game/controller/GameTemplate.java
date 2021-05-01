package game.controller;

import game.config.GameConfig;
import game.exception.GameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public abstract class GameTemplate {
    protected GameConfig config;
    protected PrintStream writer;
    protected BufferedReader reader;

    public GameTemplate(GameConfig config) {
        this.config = config;
        this.writer = new PrintStream(config.getOutputStream());
        this.reader = new BufferedReader(new InputStreamReader(config.getInputStream()));
    }

    public void run() {
        try {
            totalProcess();
        } catch (Exception e) {
            handleException(e);
        }
    }

    private void totalProcess() {
        init();

        do {
            process();
        } while(!isCompleted());

        destroy();
    }

    protected String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new GameException(e);
        }
    }

    protected abstract void init();

    protected abstract void process();

    protected abstract boolean isCompleted();

    protected abstract void destroy();

    protected abstract void handleException(Exception e);
}
