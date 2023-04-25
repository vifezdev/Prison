package gg.convict.prison.crate.listener;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import gg.convict.prison.crate.menu.CratePreviewMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class CrateListener implements Listener {

    private final CrateModule module;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                && event.getAction() != Action.LEFT_CLICK_BLOCK)
            return;

        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null
                || clickedBlock.getType() != Material.CHEST)
            return;

        Player player = event.getPlayer();
        Crate crate = module.getHandler().getCrate(
                clickedBlock.getLocation());

        if (crate == null)
            return;

        if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
            new CratePreviewMenu(crate).openMenu(player);
            return;
        }

        event.setCancelled(true);
        if (!crate.canOpen(player)) {
            player.sendMessage(CC.format(
                    "%s&cYou are not holding a &b%s&c crate key.",
                    PrisonPlugin.PREFIX,
                    crate.getDisplayName()
            ));
            return;
        }

        if (crate.getRewards().size() == 0) {
            player.sendMessage(CC.format(
                    "%s&cThere are no rewards setup for this crate.",
                    PrisonPlugin.PREFIX
            ));
            return;
        }

        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getAmount() == 1)
            player.getInventory().removeItem(itemInHand);
        else
            itemInHand.setAmount(itemInHand.getAmount() - 1);

        crate.open(player);
        player.updateInventory();
    }

}