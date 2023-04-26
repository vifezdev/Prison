package gg.convict.prison.crate.listener;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.CrateModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;
import org.hydrapvp.libraries.configuration.defaults.SimpleLocationConfig;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class CratePlaceListener implements Listener {

    private final CrateModule module;

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack itemInHand = event.getItemInHand();

        if (block == null || block.getType() != Material.CHEST
                || itemInHand == null || itemInHand.getType() != Material.CHEST
                || !itemInHand.hasItemMeta() || !itemInHand.getItemMeta().hasLore()
                || !player.hasPermission("prison.crate.place"))
            return;

        Crate crate = module.getHandler().getCrate(
                CC.strip(itemInHand.getItemMeta().getDisplayName()));

        if (crate == null)
            return;

        crate.getLocations().add(
                new SimpleLocationConfig(block.getLocation()));

        module.saveConfig();
        player.sendMessage(CC.format(
                "%sYou have placed a &b%s&f crate.",
                PrisonPlugin.PREFIX, crate.getDisplayName()
        ));
    }

}