package gg.convict.prison.scoreboard.base;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import gg.convict.prison.scoreboard.ScoreboardModule;
import gg.convict.prison.scoreboard.ScoreboardSection;
import gg.convict.prison.scoreboard.section.CombatBoardSection;
import gg.convict.prison.scoreboard.section.MineBoardSection;
import gg.convict.prison.scoreboard.section.StatsBoardSection;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.scoreboard.ScoreboardAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrisonScoreboardAdapter implements ScoreboardAdapter {

    private final ScoreboardModule module;
    private final List<ScoreboardSection> sections = new LinkedList<>();

    public PrisonScoreboardAdapter(ScoreboardModule module) {
        this.module = module;

        this.sections.addAll(Arrays.asList(
                new StatsBoardSection(),
                new CombatBoardSection(),
                new MineBoardSection()));
    }

    @Override
    public String getTitle(Player player) {
        return module.getConfig().getScoreboardTitle();
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> lines = new ArrayList<>();
        Profile profile = ProfileModule.get().getProfileHandler().getProfile(player);

        if (profile == null)
            return lines;

        lines.add("&a&7&m-------------------");

        sections.forEach(scoreboardSection -> {
            if (!scoreboardSection.canDisplay(player))
                return;

            scoreboardSection.getLines(player, profile, lines);
        });

        lines.add("&a");
        lines.add("&bconvict.gg");
        lines.add("&b&7&m-------------------");

        return lines;
    }

    @Override
    public boolean showHealth(Player player) {
        return false;
    }

}