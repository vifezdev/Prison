package gg.convict.prison.privatemine;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.position.workload.WorkloadRunnable;
import gg.convict.prison.position.workload.impl.BlockPlaceWorkload;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;
import org.hydrapvp.libraries.cuboid.Cuboid;

import java.util.UUID;

@Data
public class Mine {

    private UUID owner;
    private Cuboid cuboid;
    private Cuboid mineCuboid;
    private LocationConfig spawnLocation;

    public void resetBlocks() {
        WorkloadRunnable runnable = PrisonPlugin.get().getWorkloadRunnable();

        for (Block block : mineCuboid) {
            runnable.register(new BlockPlaceWorkload(
                    block.getWorld().getUID(),
                    block.getX(), block.getY(), block.getZ(),
                    Material.STONE
            ));
        }
    }

    public Location getCenterLocation() {
        Location center = mineCuboid.getCenter();
        center.setY(mineCuboid.getMaximumPoint().getBlockY() + 1);
        return center;
    }

    public double getAirPercentage() {
        int found = 0;

        for (Block block : mineCuboid) {
            if (block == null
                    || block.getType() == Material.AIR)
                found += 1;
        }

        return ((double) found / mineCuboid.getVolume()) * 100;
    }

    public boolean canMine(Player player, Location location) {
        if (!mineCuboid.contains(location))
            return false;

        return owner.equals(player.getUniqueId());
    }

}