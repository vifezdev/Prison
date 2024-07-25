package gg.convict.prison.privatemine.packet;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class PrisonMoveListener implements Listener {

    private final MineModule module;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getX() == to.getX()
                && from.getZ() == to.getZ())
            return;

        World mineWorld = module.getHandler().getMineWorld();

        if (!player.getWorld().equals(mineWorld)
                && player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            return;
        }

        if (player.getWorld().equals(mineWorld)) {
            Mine mine = module.getHandler().getMine(from);

            if (mine == null)
                return;

            if (!mine.getCuboid().contains(to))
                player.teleport(from);
        }
    }

}