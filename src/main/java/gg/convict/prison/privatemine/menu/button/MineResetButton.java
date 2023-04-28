package gg.convict.prison.privatemine.menu.button;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.Mine;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class MineResetButton extends Button {

    private final Mine mine;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.REDSTONE)
                .setDisplayName(CC.RED + CC.BOLD + "Reset Mine")
                .addToLore(
                        CC.format("&fCan Reset: &c%s", getCanReset()),
                        "",
                        CC.translate("&fClick to reset &cyour mine&f.")
                ).build();
    }

    public String getCanReset() {
        return "Now";
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        player.closeInventory();

        mine.resetBlocks(true);

        player.sendMessage(CC.format(
                "%sYou have reset &byour mine&f.",
                PrisonPlugin.PREFIX
        ));
    }
}