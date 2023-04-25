package gg.convict.prison.command;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.config.LocationConfig;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class SpawnCommands {

    private final PrisonPlugin plugin;

    @Command(
            names = "spawn",
            description = "Teleport to spawn",
            playerOnly = true)
    public void execute(Player player) {
        LocationConfig locationConfig = plugin.getPrisonConfig().getLocationConfig();

        if (locationConfig == null) {
            player.sendMessage(CC.RED + "No spawn location has been set.");
            return;
        }

        player.teleport(locationConfig.getLocation());
        player.sendMessage(CC.translate("&fYou have been teleported to &bSpawn&f."));
    }

    @Command(
            names = "setspawn",
            permission = "prison.command.setspawn",
            description = "Set the spawn location",
            playerOnly = true)
    public void setSpawn(Player player) {
        LocationConfig locationConfig = new LocationConfig(player.getLocation());

        plugin.getPrisonConfig().setLocationConfig(locationConfig);
        plugin.saveConfig();

        player.sendMessage(CC.translate("&fYou have set the location of &bSpawn&f."));
    }

}