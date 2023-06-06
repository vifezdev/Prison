package gg.convict.prison.warp.menu;

import gg.convict.prison.warp.Warp;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;

@RequiredArgsConstructor
public class WarpButton extends Button {

    private final Warp warp;

    @Override
    public ItemStack getItem(Player player) {
        ItemStack displayItem = warp.getDisplayItem();

        return new ItemBuilder(displayItem.getType())
                .setDisplayName(warp.getDisplayName())
                .setData(displayItem.getData().getData())
                .addToLore(
                        "",
                        "&7&oClick to teleport to this warp."
                ).build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        player.performCommand("warp " + warp.getName());
        player.closeInventory();
    }

}