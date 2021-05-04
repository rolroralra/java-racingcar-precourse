package game.config;

import game.constants.GameMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameConfigTest {

    @DisplayName("기본값_테스트")
    @Test
    public void 기본값_테스트() {
        GameConfig config = GameConfig.getDefault();
        assertThat(config)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .returns(System.in, GameConfig::getInputStream)
                .returns(System.out, GameConfig::getOutputStream)
                .returns(Locale.getDefault(), GameConfig::getLocale)
                .returns(ResourceBundle.getBundle(GameConfig.MESSAGE_BUNDLE_NAME), GameConfig::getResourceBundle);
    }

    @DisplayName("빌더_통한_객체_생성_테스트")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    public void 빌더_통한_객체_생성_테스트(String languageTag) {
        GameConfig config = GameConfig.builder()
                .inputStream(System.in)
                .outputStream(System.out)
                .locale(languageTag)
                .build();

        assertThat(config)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @DisplayName("메시지_리소스_번들이_가진_메시지_값_비교")
    @ParameterizedTest
    @ValueSource(strings = {"en", "ko"})
    public void 메시지_리소스_번들이_가진_메시지_값_비교(String languageTag) {
        GameConfig config = GameConfig.builder()
                .inputStream(System.in)
                .outputStream(System.out)
                .locale(languageTag)
                .build();

        Map<String, String> messageMap = Arrays.stream(GameMessage.values())
                .collect(Collectors.toMap(Enum::name, config::getMessage));

        assertThat(convertResourceBundleToMap(config.getResourceBundle())).containsExactlyInAnyOrderEntriesOf(messageMap);
    }

    private Map<String, String> convertResourceBundleToMap(ResourceBundle resource) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, resource.getString(key));
        }
        return map;
    }
}
