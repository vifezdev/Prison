package gg.convict.prison.util.scoreboard.nametag;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 21.10.2020 / 12:36
 * libraries / cc.invictusgames.libraries.scoreboard.nametag
 */

@AllArgsConstructor
@Data
public class NameTag {

    private String name;
    private String prefix;
    private String suffix;
    private boolean seeFriendlyInvisible;

    public NameTag(String name, String prefix, String suffix) {
        this(name, prefix, suffix, false);
    }

}
