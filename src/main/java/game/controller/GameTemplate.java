package game.controller;

import game.config.GameConfig;
import game.exception.GameException;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public abstract class GameTemplate {
    // GameConfig Bean
    protected GameConfig config;

    // Writer Bean
    protected PrintStream writer;

    // Reader Bean
    protected BufferedReader reader;

    public GameTemplate(@NonNull GameConfig config) {
        this.config = config;
        this.writer = new PrintStream(config.getOutputStream());
        this.reader = new BufferedReader(new InputStreamReader(config.getInputStream()));
    }

    public void run() {
        try {
            // Process Run Start
            totalProcess();
        } catch (Exception e) {
            // Exception Handling
            handleException(e);
        }
    }

    private void totalProcess() {
        // 1. Init
        init();

        do {
            // 2. Process during completion
            process();
        } while(!isCompleted());

        // 3. Destroy
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
