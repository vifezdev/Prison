package gg.convict.prison.scoreboard.base;

import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import gg.convict.prison.scoreboard.ScoreboardConfig;
import gg.convict.prison.scoreboard.ScoreboardModule;
import gg.convict.prison.scoreboard.ScoreboardSection;
import gg.convict.prison.scoreboard.bar.BarPosition;
import gg.convict.prison.scoreboard.section.CombatBoardSection;
import gg.convict.prison.scoreboard.section.MineBoardSection;
import gg.convict.prison.scoreboard.section.StatsBoardSection;
import gg.convict.prison.util.placeholder.PlaceholderService;
import gg.convict.prison.util.scoreboard.ScoreboardAdapter;
import org.bukkit.entity.Player;
import gg.convict.prison.util.CC;

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
                new MineBoardSection()
        ));
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

        ScoreboardConfig config = module.getConfig();

        for (String baseLine : config.getBaseLines()) {
            if (!baseLine.toLowerCase().contains("{sections}")) {
                lines.add(PlaceholderService.replace(player, baseLine));
                continue;
            }

            sections.forEach(section -> {
                BarPosition position = section.getBarPosition(player);
                if (!section.canDisplay(player))
                    return;

                if (position == BarPosition.TOP && config.isSectionsBarsEnabled())
                    lines.add(CC.format(
                            "&%s%s",
                            sections.indexOf(section),
                            config.getSectionBarText()
                    ));

                section.getLines(player, profile, lines);

                if (position == BarPosition.BOTTOM && config.isSectionsBarsEnabled())
                    lines.add(CC.format(
                            "&%s%s",
                            sections.indexOf(section) + "&a",
                            config.getSectionBarText()
                    ));
            });
        }

        return lines;
    }

    @Override
    public boolean showHealth(Player player) {
        return false;
    }

}