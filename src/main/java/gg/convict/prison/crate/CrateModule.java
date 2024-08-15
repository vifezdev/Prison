package gg.convict.prison.crate;

import com.google.common.collect.*;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.crate.command.*;
import gg.convict.prison.crate.command.parameter.*;
import gg.convict.prison.crate.listener.*;
import org.bukkit.event.Listener;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.plugin.PluginModule;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class CrateModule extends PluginModule {

    public static final DecimalFormat REWARD_FORMAT =
            new DecimalFormat("####.##");

    public CrateModule() {
        super("crates", PrisonPlugin.get(), new CrateHandler());
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new CrateEditCommand(),
                new CrateChanceCommand(),
                new CrateSetKeyCommand(),
                new CrateGiveKeyCommand(),
                new CratePlaceCommand(this),
                new CrateCreateCommand(this),
                new CrateDeleteCommand(this)
        );
    }

    @Override
    public List<Listener> getListeners() {
        return ImmutableList.of(
                new CrateListener(this),
                new CrateEditListener(this),
                new CratePlaceListener(this),
                new CrateBreakListener(this)
        );
    }

    @Override
    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of(
                Crate.class, new CrateParameter(this));
    }

    public CrateHandler getHandler() {
        return (CrateHandler) super.getConfig();
    }

}