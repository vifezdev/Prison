package gg.convict.prison.pickaxe.listener;

import gg.convict.prison.pickaxe.*;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class PickaxeListener implements Listener {

    private final PickaxeModule module;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getNewSlot());

        if (item == null
                || item.getType() != Material.DIAMOND_PICKAXE)
            return;

        PickaxeData data = setupData(player, item);
        data.getEnchantments().forEach((enchant, integer) ->
                enchant.apply(player, item, integer));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        if (event.isCancelled()
                || itemInHand == null
                || itemInHand.getType() != Material.DIAMOND_PICKAXE)
            return;

        PickaxeData data = module.getHandler().getData(itemInHand);
        if (data == null)
            return;

        data.incrementBlocksMined(1);
        data.applyItemMeta(player, itemInHand);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        ItemStack item = event.getItem().getItemStack();

        if (item.getType() == Material.DIAMOND_PICKAXE)
            setupData(event.getPlayer(), item);
    }

    public PickaxeData setupData(Player player, ItemStack item) {
        PickaxeData data = module.getHandler().getData(item);
        if (data == null) {
            data = new PickaxeData();
            data.setup(player, item);

            module.getHandler().addData(item, data);
        } else data.applyItemMeta(player, item);

        return data;
    }

}