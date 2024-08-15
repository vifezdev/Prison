package gg.convict.prison.util.plugin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gg.convict.prison.PrisonPlugin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import gg.convict.prison.util.command.parameter.ParameterType;
import gg.convict.prison.util.configuration.StaticConfiguration;

import java.io.File;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public abstract class PluginModule {

    private final String name;
    private final JavaPlugin plugin;
    private StaticConfiguration config;

    public void onEnable() {
    }

    public void onDisable() {
    }

    public List<Listener> getListeners() {
        return ImmutableList.of();
    }

    public List<Object> getCommands() {
        return ImmutableList.of();
    }

    public Map<Class<?>, ParameterType<?>> getParameterTypes() {
        return ImmutableMap.of();
    }

    public File getConfigFile() {
        return new File(plugin.getDataFolder(), name.toLowerCase() + ".json");
    }

    public static <T extends PluginModule> T getModule(Class<T> clazz) {
        return (T) PluginBootstrap.MODULES.get(clazz);
    }

    public final void saveConfig() {
        if (config == null)
            return;

        PrisonPlugin.get().getConfigService()
                .saveConfiguration(config, getConfigFile());
    }

}