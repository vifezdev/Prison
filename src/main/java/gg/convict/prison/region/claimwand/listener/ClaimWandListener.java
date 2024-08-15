package gg.convict.prison.region.claimwand.listener;

import gg.convict.prison.region.claimwand.ClaimWandHandler;
import gg.convict.prison.region.claimwand.selection.ClaimSelection;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class ClaimWandListener implements Listener {

    private final ClaimWandHandler handler;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ClaimSelection selection = handler.getSelection(player.getUniqueId());

        if (selection == null)
            return;

        ItemStack item = event.getItem();
        if (item == null)
            return;

        if (!item.isSimilar(handler.getClaimWand().toItemStack()))
            return;

        if (event.getAction() == Action.LEFT_CLICK_BLOCK
                || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            selection.setPosition(
                    event.getAction() == Action.LEFT_CLICK_BLOCK ? 1 : 2,
                    event.getClickedBlock().getLocation()
            );
            event.setCancelled(true);
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            player.setItemInHand(null);
            handler.removeSelection(player.getUniqueId());
            player.sendMessage(CC.RED + "Cleared claim selection.");
            return;
        }

        if (event.getAction() == Action.LEFT_CLICK_AIR
                && player.isSneaking()) {
            if (selection.doPurchase()) {
                player.setItemInHand(null);
                handler.removeSelection(player.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();

        if (!item.isSimilar(handler.getClaimWand().toItemStack()))
            return;

        handler.removeSelection(player.getUniqueId());
        event.getItemDrop().remove();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        handler.removeClaimWands(player);
        handler.removeSelection(player.getUniqueId());
    }
}
