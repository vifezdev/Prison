package gg.convict.prison.scoreboard;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.scoreboard.base.PrisonScoreboardAdapter;
import gg.convict.prison.scoreboard.placeholder.PrisonMinePlaceholderAdapter;
import gg.convict.prison.scoreboard.placeholder.PrisonPlaceholderAdapter;
import gg.convict.prison.util.placeholder.PlaceholderService;
import gg.convict.prison.util.plugin.PluginModule;
import gg.convict.prison.util.scoreboard.ScoreboardService;

public class ScoreboardModule extends PluginModule {

    public static int BAR_POSITION = -1;

    public ScoreboardModule() {
        super("scoreboard", PrisonPlugin.get(), new ScoreboardConfig());
    }

    @Override
    public void onEnable() {
        PlaceholderService.registerAdapter(new PrisonPlaceholderAdapter());
        PlaceholderService.registerAdapter(new PrisonMinePlaceholderAdapter());

        new ScoreboardService(
                getPlugin(),
                new PrisonScoreboardAdapter(this)
        );
    }

    public ScoreboardConfig getConfig() {
        return (ScoreboardConfig) super.getConfig();
    }

}