package gg.convict.prison.privatemine;

import com.google.common.collect.ImmutableList;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.grid.command.GridCommand;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.util.List;

public class MineModule extends PluginModule {

    public MineModule() {
        super("mines", PrisonPlugin.get(), new MineHandler());
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new GridCommand());
    }

    public MineHandler getHandler() {
        return (MineHandler) super.getConfig();
    }

    public static MineModule get() {
        return PluginModule.getModule(MineModule.class);
    }

}