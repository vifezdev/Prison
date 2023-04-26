package gg.convict.prison.crate.listener;

import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import gg.convict.prison.crate.menu.CratePreviewMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class CrateBreakListener implements Listener {

    private final CrateModule module;

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block == null
                || block.getType() != Material.CHEST)
            return;

        Player player = event.getPlayer();
        Crate crate = module.getHandler().getCrate(block.getLocation());

        if (crate == null)
            return;

        if (!player.hasPermission("prison.crate.break")
                || !player.isSneaking()
                || player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
            new CratePreviewMenu(crate).openMenu(player);
            return;
        }

        event.setCancelled(true);
        block.setType(Material.AIR);

        crate.getLocations().removeIf(simpleLocationConfig ->
                crate.hasLocation(block.getLocation()));

        module.saveConfig();
    }

}