package gg.convict.prison.crate.menu.button;

import gg.convict.prison.crate.Crate;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CrateToggleChancesButton extends Button {

    private final Crate crate;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.GOLD_INGOT)
                .setDisplayName(CC.AQUA + "Toggle Chances")
                .setLore(getLore())
                .build();
    }

    public List<String> getLore() {
        List<String> lore = new ArrayList<>();

        lore.add(CC.MENU_BAR);
        lore.add(CC.format(
                "&fCurrent Status: &b%s",
                crate.isShowChances() ? "&aOn" : "&cOff"
        ));

        lore.add("");
        lore.add(CC.translate("&7&oToggle whether the"));
        lore.add(CC.translate("&7&ocrate preview shows the"));
        lore.add(CC.translate("&7&ochances of winning items."));
        lore.add("");

        lore.add(CC.translate(crate.isShowChances()
                ? "&c&lLEFT-CLICK &cto toggle off"
                : "&a&lLEFT-CLICK &ato toggle on"
        ));
        lore.add(CC.MENU_BAR);

        return lore;
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        crate.setShowChances(!crate.isShowChances());
    }

}