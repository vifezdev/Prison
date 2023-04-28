package gg.convict.prison.privatemine.menu.button;

import gg.convict.prison.privatemine.Mine;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.github.paperspigot.Title;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class MineStatusButton extends Button {

    private final Mine mine;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.INK_SACK)
                .setDisplayName(CC.GREEN + CC.BOLD + "Mine Status")
                .setData(12)
                .addToLore(
                        CC.format("&fCurrent Status: %s", mine.isOpen() ? "&aOpen" : "&cClosed"),
                        "",
                        CC.translate("&7Click to toggle the"),
                        CC.translate("&astatus &7of &ayour mine&7.")
                ).build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        mine.setOpen(!mine.isOpen());
        player.closeInventory();

        player.sendTitle(new Title(
                CC.translate("&9&lMINE STATUS"),
                CC.format("&fYour mine is now %s", mine.isOpen() ? "&aopen" : "&cclosed"),
                20, 20, 20
        ));
    }

}