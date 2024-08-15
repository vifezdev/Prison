package gg.convict.prison.region.command;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class RegionDeleteCommand {

    private final RegionModule module;

    @Command(names = "region delete",
            permission = "prison.region.delete",
            description = "Delete a region")
    public void execute(CommandSender sender, @Param(name = "region") Region region) {
        module.deleteRegion(region);
        module.saveConfig();

        sender.sendMessage(CC.format(
                "&fDeleted region with name &b%s&f.",
                region.getDisplayName()));
    }

}