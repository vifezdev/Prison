package gg.convict.prison.privatemine.packet;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import lol.vera.spigot.packet.MovementHandler;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class PrisonPacketHandler implements MovementHandler {

    private final MineModule module;

    @Override
    public void handleMove(Player player, Location to, Location from, Object packet) {
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