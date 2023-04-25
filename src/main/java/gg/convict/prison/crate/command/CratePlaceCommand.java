package gg.convict.prison.crate.command;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import lol.vera.veraspigot.util.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;

@RequiredArgsConstructor
public class CratePlaceCommand {

    private final CrateModule module;

    @Command(names = "crate place",
            permission = "prison.crate.place",
            description = "Place a crate",
            playerOnly = true)
    public void execute(Player player, @Param(name = "crate") Crate crate) {
        ItemStack crateItem = new ItemBuilder(crate.getBlockType())
                .setDisplayName(crate.getDisplayName())
                .addToLore(
                        CC.translate("&7Place this block to"),
                        CC.format("&7setup a &b%s&7 crate.", crate.getDisplayName())
                ).glowing(true).build();

        player.getInventory().addItem(crateItem);
        player.updateInventory();

        player.sendMessage(CC.format(
                "%sYou have been given a &b%s&f crate.",
                PrisonPlugin.PREFIX, crate.getDisplayName()
        ));
    }

}