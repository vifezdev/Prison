package gg.convict.prison.scoreboard;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.scoreboard.bar.BarPosition;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.util.List;

public abstract class ScoreboardSection {

    protected final ScoreboardModule module
            = PluginModule.getModule(ScoreboardModule.class);

    public abstract void getLines(Player player, Profile profile, List<String> lines);

    public abstract boolean canDisplay(Player player);

    public BarPosition getBarPosition(Player player) {
        return BarPosition.NONE;
    }

}