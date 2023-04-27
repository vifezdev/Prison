package gg.convict.prison.pickaxe;

import com.google.common.collect.ImmutableList;
import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.pickaxe.enchant.*;
import gg.convict.prison.pickaxe.enchant.command.TestEnchantCommand;
import gg.convict.prison.pickaxe.listener.PickaxeListener;
import lombok.Getter;
import org.bukkit.event.Listener;
import org.hydrapvp.libraries.LibrariesBukkit;
import org.hydrapvp.libraries.configuration.ConfigurationService;
import org.hydrapvp.libraries.plugin.PluginModule;

import java.io.File;
import java.util.List;

@Getter
public class PickaxeModule extends PluginModule {

    private final ConfigurationService configurationService;

    private EnchantConfig enchantConfig;
    private EnchantHandler enchantHandler;

    public PickaxeModule() {
        super("pickaxe", PrisonPlugin.get(), new PickaxeHandler());

        this.configurationService = LibrariesBukkit.getInstance().getConfigurationService();
    }

    @Override
    public void onEnable() {
        this.enchantConfig = configurationService.loadConfiguration(
                EnchantConfig.class, getEnchantFile());

        this.enchantHandler = new EnchantHandler();
    }

    @Override
    public void onDisable() {
        saveEnchantConfig();
    }

    @Override
    public List<Listener> getListeners() {
        return ImmutableList.of(
                new PickaxeListener(this)
        );
    }

    @Override
    public List<Object> getCommands() {
        return ImmutableList.of(
                new TestEnchantCommand()
        );
    }

    public PickaxeHandler getHandler() {
        return (PickaxeHandler) super.getConfig();
    }

    public File getEnchantFile() {
        return new File(getPlugin().getDataFolder(),
                "enchants.json");
    }

    public void saveEnchantConfig() {
        configurationService.saveConfiguration(this.enchantConfig, this.getEnchantFile());
    }

    public static PickaxeModule get() {
        return PluginModule.getModule(PickaxeModule.class);
    }

}
