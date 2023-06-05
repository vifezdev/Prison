package gg.convict.prison.warp;

import gg.convict.prison.PrisonPlugin;
import lombok.Getter;
import org.hydrapvp.libraries.plugin.PluginModule;

@Getter
public class WarpModule extends PluginModule {

    public WarpModule() {
        super("warps", PrisonPlugin.get(), new WarpConfig());
    }

    public WarpConfig getConfig() {
        return (WarpConfig) super.getConfig();
    }

    public static WarpModule get() {
        return PluginModule.getModule(WarpModule.class);
    }

    public Warp getWarp(String name) {
        for (Warp warp : getConfig().getWarps()) {
            if (!warp.getName().equalsIgnoreCase(name))
                continue;

            return warp;
        }

        return null;
    }

}