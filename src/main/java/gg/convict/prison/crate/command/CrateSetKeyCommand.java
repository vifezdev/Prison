package gg.convict.prison.crate.command;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.crate.Crate;
import lol.vera.veraspigot.util.CC;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;

public class CrateSetKeyCommand {

    @Command(names = "crate setkey",
            permission = "prison.crate.setkey",
            description = "Set the key of a crate",
            playerOnly = true)
    public void execute(Player player, @Param(name = "crate") Crate crate) {
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand == null) {
            player.sendMessage(CC.RED + "You are not holding an item.");
            return;
        }

        crate.setKeyMaterial(itemInHand.getType());
        player.sendMessage(CC.format(
                "%sYou have set the key material type of &b%s&f to &b%s&f.",
                PrisonPlugin.PREFIX,
                crate.getDisplayName(),
                itemInHand.getType().name()
        ));
    }

}