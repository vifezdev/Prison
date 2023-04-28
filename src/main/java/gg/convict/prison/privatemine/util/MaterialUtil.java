package gg.convict.prison.privatemine.util;

import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;

public class MaterialUtil {

    public static String getName(Material material) {
        return WordUtils.capitalizeFully(material
                .name().replace("_", " "));
    }

}