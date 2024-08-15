package gg.convict.prison.privatemine.grid.menu.button;

import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.grid.SchematicType;
import gg.convict.prison.privatemine.grid.menu.GridAddMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class GridAddButton extends Button {

    private final MineHandler handler;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.DIAMOND)
                .setDisplayName(CC.AQUA + "Add Mines")
                .addToLore(
                        CC.MENU_BAR,
                        CC.format("&fMine Types: &b%s", SchematicType.values().length),
                        "",
                        CC.translate("&a&lLEFT-CLICK&a to open creation menu"),
                        CC.MENU_BAR
                ).build();
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        new GridAddMenu(handler).openMenu(player);
    }

}