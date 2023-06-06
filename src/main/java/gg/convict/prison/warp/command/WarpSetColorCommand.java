package gg.convict.prison.warp.command;

import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

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