package gg.convict.prison.scoreboard.placeholder;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import gg.convict.prison.profile.util.MoneyUtil;
import gg.convict.prison.util.placeholder.adapter.PlaceholderAdapter;
import org.bukkit.entity.Player;

public class PrisonPlaceholderAdapter implements PlaceholderAdapter {

    @Override
    public String getIdentifier() {
        return "profile";
    }

    @Override
    public String getPlaceholder(Player player, String placeholder) {
        Profile profile = ProfileModule.get().getProfileHandler().getProfile(player);

        if (profile == null)
            return null;

        switch (placeholder.toLowerCase()) {
            case "kills":
                return String.valueOf(profile.getStatistics().getKills());
            case "deaths":
                return String.valueOf(profile.getStatistics().getDeaths());
            case "kd":
                return String.valueOf(profile.getStatistics().getKd());
            case "rank":
                return String.valueOf(profile.getRank());
            case "tokens":
                return String.valueOf(profile.getTokens());
            case "balance":
                return String.valueOf(profile.getBalance());
            case "tokens-formatted":
                return MoneyUtil.format(profile.getTokens(), 0);
            case "balance-formatted":
                return MoneyUtil.format(profile.getBalance(), 0);
            default:
                return null;
        }
    }

}