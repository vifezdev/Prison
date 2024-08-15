package gg.convict.prison.util.plugin;

import gg.convict.prison.PrisonPlugin;
import lombok.Getter;
import org.bukkit.Bukkit;
import gg.convict.prison.util.command.CommandService;
import gg.convict.prison.util.configuration.StaticConfiguration;
import gg.convict.prison.util.CC;

import java.util.HashMap;
import java.util.Map;

public class PluginBootstrap {

    @Getter
    public static final Map<Class<?>, PluginModule> MODULES = new HashMap<>();

    public static void registerModules(PluginModule... pluginModules) {
        for (PluginModule module : pluginModules)
            registerModule(module);
    }

    public static void registerModule(PluginModule pluginModule) {
        long startTime = System.currentTimeMillis();
        MODULES.put(pluginModule.getClass(), pluginModule);

        StaticConfiguration config = pluginModule.getConfig();
        if (config != null)
            pluginModule.setConfig(PrisonPlugin.get().getConfigService()
                    .loadConfiguration(config.getClass(), pluginModule.getConfigFile()));

        pluginModule.getParameterTypes().forEach(CommandService::registerParameter);

        pluginModule.getListeners().forEach(listener ->
                Bukkit.getPluginManager().registerEvents(listener, pluginModule.getPlugin()));

        pluginModule.getCommands().forEach(command ->
                CommandService.register(pluginModule.getPlugin(), command));

        pluginModule.onEnable();

        PrisonPlugin.get().getLogger().info(CC.format(
                "Loaded %s module in %dms.",
                pluginModule.getName(),
                System.currentTimeMillis() - startTime));
    }

}