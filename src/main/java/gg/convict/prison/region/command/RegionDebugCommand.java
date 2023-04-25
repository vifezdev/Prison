package gg.convict.prison.region.command;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class RegionDebugCommand {

    private final RegionModule module;

    @Command(names = "region debug", playerOnly = true)
    public void execute(Player player) {
        Region region = module.fromLocation(player.getLocation());

        if (region == null) {
            player.sendMessage(CC.RED + "You are not in a region.");
            return;
        }

        player.sendMessage(CC.format(
                "&fYou are in the territory of &b%s&f.",
                region.getDisplayName()));
    }

}