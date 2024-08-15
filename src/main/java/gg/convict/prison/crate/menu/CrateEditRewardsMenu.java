package gg.convict.prison.crate.menu;

import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.listener.CrateEditListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.ItemBuilder;
import gg.convict.prison.util.menu.Menu;
import gg.convict.prison.util.CC;

@Getter
public class CrateEditRewardsMenu {

    private final Crate crate;
    private final Menu previousMenu;
    private final Inventory inventory;

    public CrateEditRewardsMenu(Crate crate, Menu previousMenu) {
        this.crate = crate;
        this.previousMenu = previousMenu;

        Inventory inventory = Bukkit.createInventory(
                null, 54, "Rewards: " + crate.getDisplayName());

        crate.getRewards().forEach(crateReward -> {
            ItemStack itemStack = crateReward.getItemStack().clone();
            ItemBuilder builder = new ItemBuilder(itemStack);

            if (itemStack.getType() != Material.STAINED_GLASS_PANE)
                builder.addToLore(CC.format(
                        "&fChance: &b%s",
                        crateReward.getChance()));

            inventory.setItem(crateReward.getSlot(), builder.build());
        });

        this.inventory = inventory;
    }

    public void openMenu(Player player) {
        player.openInventory(inventory);

        CrateEditListener.EDITING.put(
                player.getUniqueId(), this);
    }

}