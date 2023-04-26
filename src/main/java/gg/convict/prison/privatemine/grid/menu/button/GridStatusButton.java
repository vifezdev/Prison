package gg.convict.prison.privatemine.grid.menu.button;

import gg.convict.prison.privatemine.MineHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.utils.CC;

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