package gg.convict.prison.util.scoreboard.nametag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 21.10.2020 / 12:36
 * libraries / cc.invictusgames.libraries.scoreboard.nametag
 */

@AllArgsConstructor
@Getter
public abstract class NameTagAdapter {

    public static final NameTagAdapter DEFAULT = new NameTagAdapter("Default Adapter", 0) {
        @Override
        public NameTag getNameTag(Player player, Player target) {
            return new NameTag("default", "", "");
        }
    };

    private final String name;
    private final int priority;

    public abstract NameTag getNameTag(Player player, Player target);

}
