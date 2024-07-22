package gg.convict.prison.scoreboard.section;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.statistic.ProfileStatistics;
import gg.convict.prison.region.flag.RegionFlag;
import gg.convict.prison.scoreboard.ScoreboardSection;
import gg.convict.prison.scoreboard.bar.BarPosition;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.placeholder.PlaceholderService;
import org.hydrapvp.libraries.utils.CC;

import java.util.List;

public class CombatBoardSection extends ScoreboardSection {

    @Override
    public void getLines(Player player, Profile profile, List<String> lines) {
        ProfileStatistics statistics = profile.getStatistics();
        if (statistics == null)
            return;

        module.getConfig().getCombatSectionLines().forEach(s ->
                lines.add(PlaceholderService.replace(player, s)));
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