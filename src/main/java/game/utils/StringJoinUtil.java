package game.utils;

import java.util.StringJoiner;

public class StringJoinUtil {
    public static String join(Iterable<?> iterable, String delimiter) {
        return join(iterable, delimiter, "", "");
    }

    public static String join(Iterable<?> iterable, String delimiter, String prefix, String suffix) {
        final StringJoiner sj = new StringJoiner(delimiter, prefix, suffix);

        iterable.forEach(obj -> {
            sj.add(obj.toString());
        });

        return sj.toString();
    }
}
