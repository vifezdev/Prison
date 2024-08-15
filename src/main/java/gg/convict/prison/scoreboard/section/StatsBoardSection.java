package gg.convict.prison.scoreboard.section;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.util.MoneyUtil;
import gg.convict.prison.scoreboard.ScoreboardSection;
import org.bukkit.entity.Player;
import gg.convict.prison.util.placeholder.PlaceholderService;

import java.util.List;

public class StatsBoardSection extends ScoreboardSection {

    @Override
    public void getLines(Player player, Profile profile, List<String> lines) {
        module.getConfig().getStatsSectionLines().forEach(s ->
                lines.add(PlaceholderService.replace(player, s)));
    }

    @Override
    public boolean canDisplay(Player player) {
        return true;
    }

}