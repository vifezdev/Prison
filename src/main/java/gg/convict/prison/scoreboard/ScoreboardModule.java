package gg.convict.prison.scoreboard;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.scoreboard.base.PrisonScoreboardAdapter;
import org.hydrapvp.libraries.plugin.PluginModule;
import org.hydrapvp.libraries.scoreboard.ScoreboardService;

public class ScoreboardModule extends PluginModule {

    public ScoreboardModule() {
        super("scoreboard", PrisonPlugin.get(), new ScoreboardConfig());
    }

    @Override
    public void onEnable() {
        new ScoreboardService(getPlugin(),
                new PrisonScoreboardAdapter(this));
    }

    public ScoreboardConfig getConfig() {
        return (ScoreboardConfig) super.getConfig();
    }

}