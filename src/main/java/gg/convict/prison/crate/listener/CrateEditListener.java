package gg.convict.prison.crate.listener;

import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import gg.convict.prison.crate.menu.CrateEditRewardsMenu;
import gg.convict.prison.crate.reward.CrateReward;
import gg.convict.prison.crate.util.CrateUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class CrateEditListener implements Listener {

    public static Map<UUID, CrateEditRewardsMenu> EDITING = new HashMap<>();
    private final CrateModule module;

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        HumanEntity player = event.getPlayer();
        if (!EDITING.containsKey(player.getUniqueId()))
            return;

        Inventory inventory = event.getInventory();
        if (!inventory.getTitle().contains("Rewards: ")
                || !player.hasPermission("prison.command.edit"))
            return;

        Crate crate = EDITING.get(player.getUniqueId()).getCrate();
        if (crate == null) {
            EDITING.remove(player.getUniqueId());
            return;
        }

        crate.getRewards().clear();

        for (int i = 0; i < inventory.getContents().length; i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.AIR)
                continue;

            CrateUtil.removeChance(item);
            crate.getRewards().add(
                    new CrateReward(item, i));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uniqueId = event.getPlayer().getUniqueId();
        if (!EDITING.containsKey(uniqueId))
            return;

        EDITING.get(uniqueId).getPreviousMenu()
                .openMenu(event.getPlayer());

        EDITING.remove(uniqueId);
    }
}
