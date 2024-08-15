package gg.convict.prison.broadcast;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.broadcast.runnable.BroadcastRunnable;
import org.bukkit.Bukkit;
import gg.convict.prison.util.plugin.PluginModule;

public class BroadcastModule extends PluginModule {

    public BroadcastModule() {
        super("broadcasts", PrisonPlugin.get(), new BroadcastConfig());
    }

    @Override
    public void onEnable() {
        int delayTicks = getConfig().getBroadcastDelayTicks();

        Bukkit.getScheduler().runTaskTimerAsynchronously(
                getPlugin(),
                new BroadcastRunnable(this),
                delayTicks, delayTicks
        );
    }

    public BroadcastConfig getConfig() {
        return (BroadcastConfig) super.getConfig();
    }

}