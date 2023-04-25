package gg.convict.prison.profile;

import com.google.common.collect.*;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.profile.command.*;
import gg.convict.prison.profile.command.parameter.ProfileParameter;
import gg.convict.prison.profile.listener.ProfileListener;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.hydrapvp.libraries.command.parameter.ParameterType;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.util.*;

@Getter
public class ProfileModule extends PluginModule {

    private ProfileHandler profileHandler;

    public ProfileModule() {
        super("profiles", PrisonPlugin.get(), null);
    }

    @Override
    public void onEnable() {
        this.profileHandler = new ProfileHandler();
    }

    @Override
    public void onDisable() {
        profileHandler.getProfileMap().values().forEach(Profile::save);
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new TokenCommands(),
                new BalanceCommands());
    }

    @Override
    public List<Listener> getListeners() {
        return ImmutableList.of(
                new ProfileListener(this));
    }

    @Override
    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of(
                Profile.class, new ProfileParameter(this));
    }

    public static ProfileModule get() {
        return PluginModule.getModule(ProfileModule.class);
    }

}