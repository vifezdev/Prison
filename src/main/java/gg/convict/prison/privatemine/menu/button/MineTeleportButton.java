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
public class MineTeleportButton extends Button {

    private final Mine mine;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.WOOD_DOOR)
                .setDisplayName(CC.AQUA + CC.BOLD + "Teleport to mine")
                .addToLore(
                        "",
                        CC.translate("&fClick to teleport to"),
                        CC.translate("&fyour &bprivate mine&7.")
                ).build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        player.closeInventory();
        mine.teleport(player);

        player.sendTitle(new Title(
                CC.translate("&d&lTELEPORTED"),
                CC.translate("&fYou have been teleported to your mine"),
                20, 20, 20
        ));
    }

}