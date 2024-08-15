package gg.convict.prison.region.command;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class RegionColorCommand {

    private final RegionModule module;

    @Command(names = "region setcolor",
            permission = "prison.region.setcolor",
            description = "Set the color of a region")
    public void execute(CommandSender sender,
                        @Param(name = "region") Region region,
                        @Param(name = "color") ChatColor color) {
        String previousName = region.getDisplayName();

        region.setColor(color);
        region.save();

        sender.sendMessage(CC.format(
                "&fSet the color of &b%s&f to &r%sthis&f.",
                previousName, color));
    }

}