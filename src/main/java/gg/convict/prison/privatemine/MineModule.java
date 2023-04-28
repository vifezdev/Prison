package gg.convict.prison.privatemine;

import com.google.common.collect.ImmutableList;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.allocation.MineAllocateCommand;
import gg.convict.prison.privatemine.command.PrivateMineCommand;
import gg.convict.prison.privatemine.grid.command.*;
import gg.convict.prison.privatemine.listener.MineJoinListener;
import gg.convict.prison.privatemine.listener.MineListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.util.List;

public class MineModule extends PluginModule {

    public MineModule() {
        super("mines", PrisonPlugin.get(), new MineHandler());
    }

    @Override
    public void onEnable() {
        MineHandler handler = getHandler();

        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
            handler.getUsedMines().forEach(Mine::resetBlocks);
            handler.getFreeMines().forEach(Mine::resetBlocks);
        }, 10L);
    }

    @Override
    public List<Listener> getListeners() {
        return ImmutableList.of(
                new MineListener(this),
                new MineJoinListener(this)
        );
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new MineAllocateCommand(this),

                new GridCommand(),
                new GridTeleportCommand(),
                new PrivateMineCommand(this)
        );
    }

    public MineHandler getHandler() {
        return (MineHandler) super.getConfig();
    }

    public static MineModule get() {
        return PluginModule.getModule(MineModule.class);
    }

}