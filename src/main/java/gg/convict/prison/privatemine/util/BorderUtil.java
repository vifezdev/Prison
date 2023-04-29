package gg.convict.prison.privatemine.util;

import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.WorldBorder;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

public class BorderUtil {

    public static void sendBorder(Player player, Location location, int size) {
        WorldBorder worldBorder = new WorldBorder();
        worldBorder.world = ((CraftWorld) location.getWorld()).getHandle();

        worldBorder.setCenter(
                location.getBlockX() + 0.5,
                location.getBlockZ() + 0.5
        );

        worldBorder.setSize(size);
        worldBorder.setWarningTime(0);
        worldBorder.setWarningDistance(0);

        player.sendPacket(new PacketPlayOutWorldBorder(
                worldBorder,
                PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE
        ));
    }

}