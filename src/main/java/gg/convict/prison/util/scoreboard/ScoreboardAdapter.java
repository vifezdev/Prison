package gg.convict.prison.util.scoreboard;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 21.10.2020 / 07:46
 * libraries / cc.invictusgames.libraries.scoreboard
 */

public interface ScoreboardAdapter {

    String getTitle(Player player);

    List<String> getLines(Player player);

    boolean showHealth(Player player);

}
