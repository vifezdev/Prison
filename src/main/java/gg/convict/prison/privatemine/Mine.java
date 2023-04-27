package gg.convict.prison.privatemine;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.position.workload.WorkloadRunnable;
import gg.convict.prison.position.workload.impl.BlockPlaceWorkload;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.block.Block;
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

}