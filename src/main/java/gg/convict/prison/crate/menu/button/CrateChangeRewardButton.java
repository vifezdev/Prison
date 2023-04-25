package gg.convict.prison.crate.menu.button;

import gg.convict.prison.crate.Crate;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.builder.ItemBuilder;
import org.hydrapvp.libraries.menu.Button;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CrateChangeRewardButton extends Button {

    private final Crate crate;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.TRIPWIRE_HOOK)
                .setDisplayName(CC.AQUA + "Rewards Per Key")
                .setLore(getLore())
                .build();
    }

    public List<String> getLore() {
        List<String> lore = new ArrayList<>();

        lore.add(CC.MENU_BAR);
        lore.add(CC.format(
                "&fCurrent Amount: &b%s",
                crate.getItemsPerKey()
        ));

        lore.add("");
        lore.add(CC.translate("&7&oThe amount of rewards a"));
        lore.add(CC.translate("&7&oplayer receives per key."));
        lore.add("");

        lore.add(CC.translate("&a&lLEFT-CLICK &ato increase by 1"));
        lore.add(CC.translate("&c&lRIGHT-CLICK &cto decrease by 1"));
        lore.add(CC.MENU_BAR);

        return lore;
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        int itemsPerKey = crate.getItemsPerKey();

        if (clickType.isRightClick()) {
            if (itemsPerKey <= 1)
                return;

            crate.setItemsPerKey(itemsPerKey - 1);
            return;
        }

        crate.setItemsPerKey(itemsPerKey + 1);
    }

}