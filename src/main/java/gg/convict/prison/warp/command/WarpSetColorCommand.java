package gg.convict.prison.warp.command;

import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class WarpSetColorCommand {

    private final WarpModule module;

    @Command(names = "warp setcolor",
            description = "Set the color of a warp",
            permission = "prison.warp.setcolor")
    public void execute(CommandSender sender,
                        @Param(name = "warp") Warp warp,
                        @Param(name = "color") ChatColor color) {
        ChatColor previousColor = warp.getDisplayColor();

        warp.setDisplayColor(color);
        warp.save();

        sender.sendMessage(CC.format(
                "&fSet the color of &b%s&f to &r%sthis&f.",
                previousColor + warp.getName(), color));
    }

}