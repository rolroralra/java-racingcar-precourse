package game.constants;

import game.config.GameConfig;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class RacingGameMessageTest {
    @Test
    void 메시지_리소스_번들_목록과_비교() {
        Map<String, String> messageKeySampleFormatMap = Arrays.stream(RacingGameMessage.values())
                .collect(Collectors.toMap(RacingGameMessage::name, RacingGameMessage::getSampleMessage));

        assertThat(messageKeySampleFormatMap).containsExactlyInAnyOrderEntriesOf(convertResourceBundleToMap());
    }

    private Map<String, String> convertResourceBundleToMap() {
        ResourceBundle resource = ResourceBundle.getBundle(GameConfig.MESSAGE_BUNDLE_NAME);

        Map<String, String> map = new HashMap<>();
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, resource.getString(key));
        }

        return map;
    }

}