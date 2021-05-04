package game.constants;

import game.config.GameConfig;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


class GameMessageTest {
    @Test
    void 메시지_리소스_번들_목록과_비교() {
        List<String> messageKeyList = Arrays.stream(GameMessage.values())
                .map(GameMessage::getKey)
                .collect(Collectors.toList());

        List<String> resourceBundleKeyList = getResourceBundleKeyList();

        assertThat(messageKeyList)
                .containsExactlyInAnyOrderElementsOf(resourceBundleKeyList)
                .hasSameSizeAs(resourceBundleKeyList);
    }

    private List<String> getResourceBundleKeyList() {
        ResourceBundle resource = ResourceBundle.getBundle(GameConfig.MESSAGE_BUNDLE_NAME);

        List<String> resourceBundleKeyList = new ArrayList<>();
        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            resourceBundleKeyList.add(key);
        }

        return resourceBundleKeyList;
    }

}