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
public class CrateToggleBroadcastButton extends Button {

    private final Crate crate;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.JUKEBOX)
                .setDisplayName(CC.AQUA + "Toggle Broadcasts")
                .setLore(getLore())
                .build();
    }

    public List<String> getLore() {
        List<String> lore = new ArrayList<>();

        lore.add(CC.MENU_BAR);
        lore.add(CC.format(
                "&fCurrent Status: &b%s",
                crate.isBroadcastRewards() ? "&aOn" : "&cOff"
        ));

        lore.add("");
        lore.add(CC.translate("&7&oToggle whether the crate"));
        lore.add(CC.translate("&7&obroadcasts the rewards of"));
        lore.add(CC.translate("&7&owhich a player receives."));
        lore.add("");

        lore.add(CC.translate(crate.isBroadcastRewards()
                ? "&c&lLEFT-CLICK &cto toggle off"
                : "&a&lLEFT-CLICK &ato toggle on"
        ));
        lore.add(CC.MENU_BAR);

        return lore;
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        crate.setBroadcastRewards(!crate.isBroadcastRewards());
    }

}