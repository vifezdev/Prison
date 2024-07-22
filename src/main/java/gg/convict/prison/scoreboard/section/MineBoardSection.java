package gg.convict.prison.scoreboard.section;

import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.profile.Profile;
import gg.convict.prison.scoreboard.ScoreboardSection;
import gg.convict.prison.scoreboard.bar.BarPosition;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.placeholder.PlaceholderService;

import java.util.List;

public class MineBoardSection extends ScoreboardSection {

    @Override
    public void getLines(Player player, Profile profile, List<String> lines) {
        module.getConfig().getMiningSectionLines().forEach(s ->
                lines.add(PlaceholderService.replace(player, s)));
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