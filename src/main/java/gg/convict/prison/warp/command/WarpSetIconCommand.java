package gg.convict.prison.warp.command;

import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;
import org.hydrapvp.libraries.utils.ItemUtils;

@RequiredArgsConstructor
public class WarpSetIconCommand {

    private final WarpModule module;

    @Command(names = "warp seticon",
            description = "Set the icon of a warp",
            permission = "prison.warp.seticon",
            playerOnly = true)
    public void execute(Player player, @Param(name = "warp") Warp warp) {
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            player.sendMessage(CC.RED + "You must be holding an item.");
            return;
        }

        warp.setDisplayItem(itemInHand);
        warp.save();

        player.sendMessage(CC.format(
                "&fSet the icon of &b%s&f to &b%s&f.",
                warp.getDisplayColor(),
                ItemUtils.getName(itemInHand)
        ));
    }

}