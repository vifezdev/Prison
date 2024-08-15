package gg.convict.prison.crate.command;

import gg.convict.prison.config.PrisonBranding;
import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import gg.convict.prison.util.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;

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
                PrisonBranding.get().getPrefix(), crate.getDisplayName()
        ));
    }

}