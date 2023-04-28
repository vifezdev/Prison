package gg.convict.prison.region.listener;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.region.flag.RegionFlag;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class RegionListeners implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location blockLocation = event.getBlock().getLocation();
        Mine mine = MineModule.get().getHandler().getMine(blockLocation);

        if (mine != null && !player.hasMetadata("Build")) {
            event.setCancelled(!mine.canMine(player, blockLocation));
            return;
        }

        if (!RegionFlag.CAN_BREAK.isApplicableAt(blockLocation)
                && !player.hasMetadata("Build"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!RegionFlag.CAN_PLACE.isApplicableAt(event.getBlock().getLocation())
                && !event.getPlayer().hasMetadata("Build"))
            event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!RegionFlag.CAN_DAMAGE.isApplicableAt(
                event.getEntity().getLocation()))
            event.setCancelled(true);
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (!RegionFlag.CAN_DAMAGE.isApplicableAt(event.getEntity().getLocation())
                || !RegionFlag.CAN_DAMAGE.isApplicableAt(event.getDamager().getLocation()))
            event.setCancelled(true);
    }

}