package gg.convict.prison.region.command;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

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