package gg.convict.prison.scoreboard.section;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.util.MoneyUtil;
import gg.convict.prison.scoreboard.ScoreboardSection;
import org.bukkit.entity.Player;

import java.util.List;

public class StatsBoardSection extends ScoreboardSection {

    @Override
    public void getLines(Player player, Profile profile, List<String> lines) {
        lines.add("&b&l ▎ &3" + player.getName());
        lines.add("&b&l ▎ &fRank: &b" + profile.getRank());
        lines.add("&b&l ▎ &fTokens: &3⛃&b" + MoneyUtil.format(profile.getTokens(), 0));
        lines.add("&b&l ▎ &fBalance: &2$&a" + MoneyUtil.format(profile.getBalance(), 0));
    }

    @Override
    public boolean canDisplay(Player player) {
        return true;
    }

}