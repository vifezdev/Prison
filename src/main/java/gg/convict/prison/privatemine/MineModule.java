package gg.convict.prison.privatemine;

import gg.convict.prison.PrisonPlugin;
import org.hydrapvp.libraries.plugin.PluginModule;

public class MineModule extends PluginModule {

    public MineModule() {
        super("mines", PrisonPlugin.get(), new MineHandler());
    }

    public MineHandler getHandler() {
        return (MineHandler) super.getConfig();
    }

    public static MineModule get() {
        return PluginModule.getModule(MineModule.class);
    }

}