package gg.convict.prison.scoreboard;

import gg.convict.prison.profile.Profile;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class ScoreboardSection {

    public abstract void getLines(Player player, Profile profile, List<String> lines);

    public abstract boolean canDisplay(Player player);

}