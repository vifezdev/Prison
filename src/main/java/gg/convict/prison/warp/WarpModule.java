package gg.convict.prison.warp;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.util.plugin.PluginModule;
import gg.convict.prison.warp.command.*;
import gg.convict.prison.warp.command.parameter.WarpParameter;
import lombok.Getter;
import gg.convict.prison.util.command.parameter.ParameterType;

import java.util.List;
import java.util.Map;

@Getter
public class WarpModule extends PluginModule {

    public WarpModule() {
        super("warps", PrisonPlugin.get(), new WarpConfig());
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new WarpCommand(this),
                new WarpCreateCommand(this),
                new WarpDeleteCommand(this),
                new WarpSetIconCommand(this),
                new WarpSetColorCommand(this),
                new WarpSetLocationCommand(this)
        );
    }

    @Override
    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of(
                Warp.class, new WarpParameter(this)
        );
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