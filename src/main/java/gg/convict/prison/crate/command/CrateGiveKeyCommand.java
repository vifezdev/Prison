package gg.convict.prison.crate.command;

import gg.convict.prison.config.PrisonBranding;
import gg.convict.prison.crate.Crate;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

public class CrateGiveKeyCommand {

    @Command(names = "crate givekey",
            permission = "prison.crate.givekey",
            description = "Give a player a crate key")
    public void execute(CommandSender sender,
                        @Param(name = "target") Player target,
                        @Param(name = "crate") Crate crate,
                        @Param(name = "amount") int amount) {
        ItemStack itemStack = crate.buildKey();
        itemStack.setAmount(amount);

        target.getInventory().addItem(itemStack);
        sender.sendMessage(CC.format(
                "%sYou have given &b%s &b%s Crate Key%s&f to &b%s&f.",
                PrisonBranding.get().getPrefix(), amount,
                crate.getDisplayName(),
                amount == 1 ? "" : "s",
                target.getDisplayName()
        ));

        target.sendMessage(CC.format(
                "%sYou have been given &b%s &b%s Crate Key%s&f.",
                PrisonBranding.get().getPrefix(), amount,
                crate.getDisplayName(),
                amount == 1 ? "" : "s"
        ));
    }

}