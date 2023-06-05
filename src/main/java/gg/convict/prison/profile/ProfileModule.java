package gg.convict.prison.profile;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.profile.command.BalanceCommands;
import gg.convict.prison.profile.command.TokenCommands;
import gg.convict.prison.profile.command.parameter.ProfileParameter;
import gg.convict.prison.profile.listener.ProfileListener;
import gg.convict.prison.profile.minecrate.MineCrateHandler;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.util.List;
import java.util.Map;

@Getter
public class ProfileModule extends PluginModule {

    private ProfileHandler profileHandler;
    private MineCrateHandler minecrateHandler;

    public ProfileModule() {
        super("profiles", PrisonPlugin.get(), null);
    }

    @Override
    public void onEnable() {
        this.profileHandler = new ProfileHandler(PrisonPlugin.get());
        this.minecrateHandler = new MineCrateHandler(PrisonPlugin.get());
    }

    @Override
    public void onDisable() {
        profileHandler.saveProfiles();
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new TokenCommands(),
                new BalanceCommands()
        );
    }

    @Override
    public List<Listener> getListeners() {
        return ImmutableList.of(
                new ProfileListener(this)
        );
    }

    @Override
    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of(
                Profile.class, new ProfileParameter()
        );
    }

    public static ProfileModule get() {
        return PluginModule.getModule(ProfileModule.class);
    }

}