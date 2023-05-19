package gg.convict.prison.scoreboard.section;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.statistic.ProfileStatistics;
import gg.convict.prison.region.flag.RegionFlag;
import gg.convict.prison.scoreboard.ScoreboardSection;
import gg.convict.prison.scoreboard.bar.BarPosition;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.utils.CC;

import java.util.List;

public class CombatBoardSection extends ScoreboardSection {

    @Override
    public void getLines(Player player, Profile profile, List<String> lines) {
        ProfileStatistics statistics = profile.getStatistics();
        if (statistics == null)
            return;

        lines.add("&b&l ▎ &3Combat Stats");
        lines.add("&b&l ▎ &fKills: &b" + statistics.getKills());
        lines.add("&b&l ▎ &fDeaths: &b" + statistics.getDeaths());
        lines.add("&b&l ▎ &fKDR: &b" + statistics.getKd());
    }

    @Override
    public boolean canDisplay(Player player) {
        return RegionFlag.CAN_DAMAGE.isApplicableAt(player.getLocation());
    }

    @Override
    public BarPosition getBarPosition(Player player) {
        return BarPosition.TOP;
    }

}