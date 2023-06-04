package gg.convict.prison.leaderboard;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.leaderboard.command.LeaderboardCommand;
import gg.convict.prison.leaderboard.command.parameter.LeaderboardParameter;
import gg.convict.prison.leaderboard.impl.*;
import lombok.Getter;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Getter
public class LeaderboardModule extends PluginModule {

    private final List<AbstractLeaderboard<?>> leaderboards = new ArrayList<>();

    public LeaderboardModule() {
        super("leaderboards", PrisonPlugin.get(), new LeaderboardHandler());

        leaderboards.addAll(Arrays.asList(
                new TopTokensLeaderboard(),
                new TopBalanceLeaderboard()
        ));
    }

    @Override
    public void onEnable() {
        PrisonPlugin.EXECUTOR.scheduleAtFixedRate(
                () -> leaderboards.forEach(AbstractLeaderboard::refresh),
                0,
                1,
                TimeUnit.MINUTES
        );
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new LeaderboardCommand()
        );
    }

    @Override
    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of(
                AbstractLeaderboard.class, new LeaderboardParameter()
        );
    }

    public boolean canDisplay(UUID uuid) {
        return !getHandler().getExceptions().contains(uuid);
    }

    public LeaderboardHandler getHandler() {
        return (LeaderboardHandler) super.getConfig();
    }

    public static LeaderboardModule get() {
        return PluginModule.getModule(LeaderboardModule.class);
    }

    public AbstractLeaderboard<?> getLeaderboard(String input) {
        for (AbstractLeaderboard<?> leaderboard : leaderboards) {
            if (leaderboard.getId().equalsIgnoreCase(input))
                return leaderboard;
        }

        return null;
    }
}