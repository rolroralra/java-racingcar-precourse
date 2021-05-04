package game.config;

import game.constants.GameMessage;
import lombok.Getter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

@Getter
public class GameConfig {
    public static final String MESSAGE_BUNDLE_NAME = "message";
    private static final InputStream DEFAULT_INPUT_STREAM = System.in;
    private static final OutputStream DEFAULT_OUTPUT_STREAM = System.out;
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Locale locale;
    private final ResourceBundle resourceBundle;

    public static class Builder {
        private InputStream inputStream;
        private OutputStream outputStream;
        private Locale locale;

        public Builder() {
            this.inputStream = DEFAULT_INPUT_STREAM;
            this.outputStream = DEFAULT_OUTPUT_STREAM;
            this.locale = DEFAULT_LOCALE;
        }

        public Builder inputStream(InputStream inputStream) {
            this.inputStream = inputStream;
            return this;
        }

        public Builder outputStream(OutputStream outputStream) {
            this.outputStream = outputStream;
            return this;
        }

        public Builder locale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder locale(String languageTag) {
            return locale(Locale.forLanguageTag(languageTag));
        }

        public GameConfig build() {
            return new GameConfig(this.inputStream, this.outputStream, this.locale);
        }
    }

    private GameConfig(InputStream inputStream, OutputStream outputStream, Locale locale) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.locale = locale;
        this.resourceBundle = ResourceBundle.getBundle(MESSAGE_BUNDLE_NAME, locale);
    }

    public static GameConfig getDefault() {
        return new GameConfig(DEFAULT_INPUT_STREAM, DEFAULT_OUTPUT_STREAM, DEFAULT_LOCALE);
    }

    public String getMessage(String key) {
        return this.resourceBundle.getString(key);
    }

    public String getMessage(GameMessage gameMessage) {
        return this.getMessage(gameMessage.name());
    }

    public static Builder builder() {
        return new Builder();
    }

}
