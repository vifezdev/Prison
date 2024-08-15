package gg.convict.prison.util;

import java.util.List;

public class LoreUtil {

    public static String getValue(String key, List<String> lore) {
        key = key + ": ";

        for (String line : lore) {
            line = CC.strip(line);

            if (!line.contains(key))
                continue;

            return line.replace(key, "");
        }

        return null;
    }

}