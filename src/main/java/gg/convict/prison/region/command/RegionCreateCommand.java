package gg.convict.prison.region.command;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

import java.util.UUID;

@RequiredArgsConstructor
public class RegionCreateCommand {

    private final RegionModule module;

    @Command(names = "region create",
            permission = "prison.region.create",
            description = "Create a new region")
    public void execute(CommandSender sender, @Param(name = "name") String name) {
        Region region = module.getRegion(name);

        if (region != null) {
            sender.sendMessage(CC.RED + "A region with that name already exists.");
            return;
        }

        region = new Region(UUID.randomUUID(), name, null, ChatColor.WHITE);
        module.getConfig().addRegion(region);

        sender.sendMessage(CC.format(
                "&fCreated region with name &b%s&f.",
                region.getName()));
    }

}