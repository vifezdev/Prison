package gg.convict.prison.privatemine;

import com.google.common.collect.ImmutableList;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.command.PrivateMineCommand;
import gg.convict.prison.privatemine.grid.command.*;
import gg.convict.prison.privatemine.listener.MineJoinListener;
import gg.convict.prison.privatemine.listener.MineListener;
import gg.convict.prison.privatemine.packet.PrisonPacketHandler;
import lol.vera.veraspigot.VeraSpigot;
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

        VeraSpigot.INSTANCE.registerMovementHandlers(
                getPlugin(),
                new PrisonPacketHandler(this)
        );

        Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
            handler.getUsedMines().forEach(mine -> mine.resetBlocks(false));
            handler.getFreeMines().forEach(mine -> mine.resetBlocks(false));
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