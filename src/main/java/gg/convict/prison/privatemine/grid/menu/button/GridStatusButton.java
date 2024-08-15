package gg.convict.prison.privatemine.grid.menu.button;

import gg.convict.prison.privatemine.MineHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class GridStatusButton extends Button {

    private final MineHandler handler;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.IRON_INGOT)
                .setDisplayName(CC.AQUA + "Grid Status")
                .addToLore(
                        CC.MENU_BAR,
                        CC.format("&fBusy: %s", handler.getMineGrid().isCanPaste() ? "&cFalse" : "&aTrue"),
                        CC.format("&fFree Mines: &b%s", handler.getFreeMines().size()),
                        CC.MENU_BAR
                ).build();
    }

}