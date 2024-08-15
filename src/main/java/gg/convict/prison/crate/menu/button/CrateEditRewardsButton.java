package gg.convict.prison.crate.menu.button;

import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.menu.CrateEditRewardsMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Button;
import gg.convict.prison.util.menu.Menu;
import gg.convict.prison.util.CC;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CrateEditRewardsButton extends Button {

    private final Crate crate;
    private final Menu previousMenu;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.CHEST)
                .setDisplayName(CC.AQUA + "Edit Rewards")
                .setLore(getLore())
                .build();
    }

    public List<String> getLore() {
        List<String> lore = new ArrayList<>();

        lore.add(CC.MENU_BAR);
        lore.add(CC.format(
                "&fCurrent Rewards: &b%s",
                crate.getRealRewards()
        ));

        lore.add("");
        lore.add(CC.translate("&7&oThe rewards a player"));
        lore.add(CC.translate("&7&ocan win from the crate."));
        lore.add("");

        lore.add(CC.translate("&a&lLEFT-CLICK &ato edit"));
        lore.add(CC.MENU_BAR);

        return lore;
    }

    @Override
    public void click(Player player, int slot, ClickType clickType, int hotbarButton) {
        new CrateEditRewardsMenu(crate, previousMenu).openMenu(player);
    }

}