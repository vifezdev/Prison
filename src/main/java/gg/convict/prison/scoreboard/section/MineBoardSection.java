package gg.convict.prison.scoreboard.section;

import gg.convict.core.util.SenderUtil;
import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.profile.Profile;
import gg.convict.prison.scoreboard.ScoreboardSection;
import gg.convict.prison.scoreboard.bar.BarPosition;
import org.bukkit.entity.Player;

import java.util.List;

public class MineBoardSection extends ScoreboardSection {

    @Override
    public void getLines(Player player, Profile profile, List<String> lines) {
        Mine mine = MineModule.get().getHandler().getMine(player.getLocation());
        lines.add("&b&l ▎ &3Mine");
        lines.add("&b&l ▎ &fOwner: &b" + SenderUtil.getName(mine.getOwner()));
        lines.add("&b&l ▎ &fBlocks Left: &b" + Math.round(100 - mine.getAirPercentage()) + "%");
    }

    @Override
    public boolean canDisplay(Player player) {
        return MineModule.get().getHandler().getMine(player.getLocation()) != null;
    }

    @Override
    public BarPosition getBarPosition(Player player) {
        return BarPosition.TOP;
    }

}