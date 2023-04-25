package gg.convict.prison.crate.menu.button;

import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.util.DyeUtil;
import lol.vera.veraspigot.menu.general.ColorMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.menu.Menu;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CrateChangeColorButton extends Button {

    private final Crate crate;
    private final Menu previousMenu;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.INK_SACK)
                .setData(DyeUtil.getDyeColor(crate.getColor()))
                .setDisplayName(CC.AQUA + "Change Color")
                .setLore(getLore())
                .build();
    }

    public List<String> getLore() {
        List<String> lore = new ArrayList<>();

        lore.add(CC.MENU_BAR);
        lore.add(CC.format(
                "&fCurrent Color: &f%sThis",
                crate.getColor()
        ));

        lore.add("");
        lore.add(CC.translate("&7&oThe color displayed when"));
        lore.add(CC.translate("&7&oviewing the crate."));
        lore.add("");

        lore.add(CC.translate(
                "&a&lLEFT-CLICK &achange color"));
        lore.add(CC.MENU_BAR);

        return lore;
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        new ColorMenu(chatColor -> {
            player.closeInventory();

            crate.setColor("&" + chatColor.getChar());
            previousMenu.openMenu(player);
        }).openInventory(player);
    }

}