package gg.convict.prison.util;

import lombok.Getter;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 17.03.2020 / 18:17
 * libraries / cc.invictusgames.libraries.utils
 */

public class UsageFlag {

    @Getter private final String flag;
    @Getter private final String description;

    public UsageFlag(String flag, String description) {
        this.flag = flag;
        this.description = description;
    }
}
