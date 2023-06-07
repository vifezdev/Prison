package gg.convict.prison.crate.command;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.config.PrisonBranding;
import org.hydrapvp.libraries.utils.CC;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.ItemNbtUtil;

public class CrateChanceCommand {

    @Command(names = "crate chance",
            permission = "prison.crate.chance",
            description = "View the chance of a reward",
            playerOnly = true)
    public void execute(Player player) {
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null) {
            player.sendMessage(CC.RED + "You are not holding an item.");
            return;
        }

        double chance = ItemNbtUtil.getDouble(itemInHand, "chance");
        player.sendMessage(CC.format(
                "%sThe chance of this reward is &b%s&f.",
                PrisonBranding.get().getPrefix(), chance
        ));
    }

    @Command(names = "crate chance set",
            permission = "prison.crate.chance",
            description = "Set the chance of a reward",
            playerOnly = true)
    public void set(Player player, @Param(name = "chance") double chance) {
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null) {
            player.sendMessage(CC.RED + "You are not holding an item.");
            return;
        }

        if (chance < 0 || chance > 100) {
            player.sendMessage(CC.RED + "The chance must be between 0 and 100.");
            return;
        }

        ItemNbtUtil.set(itemInHand, "chance", chance);
        player.sendMessage(CC.format(
                "%sSet the chance of this reward to &b%s&f.",
                PrisonBranding.get().getPrefix(), chance
        ));
    }

}