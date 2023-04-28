package gg.convict.prison.privatemine;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.grid.SchematicType;
import lol.vera.veraspigot.util.CC;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.github.paperspigot.Title;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;
import org.hydrapvp.libraries.cuboid.Cuboid;
import org.hydrapvp.libraries.workload.WorkloadRunnable;
import org.hydrapvp.libraries.workload.impl.BlockPlaceWorkload;

import java.util.*;

@Data
public class Mine {

    private UUID owner;

    private Cuboid cuboid;
    private Cuboid mineCuboid;

    private SchematicType type;
    private LocationConfig spawnLocation;
    private Material blockMaterial = Material.STONE;

    private boolean open = false;
    private final Map<UUID, Boolean> memberAccess = new HashMap<>();

    public void resetBlocks(boolean teleportPlayers) {
        WorkloadRunnable runnable = PrisonPlugin.get().getWorkloadRunnable();

        for (Block block : mineCuboid) {
            runnable.register(new BlockPlaceWorkload(
                    block.getWorld().getUID(),
                    block.getX(), block.getY(), block.getZ(),
                    blockMaterial
            ));
        }

        if (teleportPlayers)
            getPlayersInMine().forEach(inMine -> {
                inMine.teleport(getCenterLocation());

                inMine.sendTitle(new Title(
                        CC.translate("&b&lMINE RESET"),
                        CC.translate("&fThe mine you were in has been reset"),
                        20, 80, 20
                ));
            });
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

        UUID uuid = player.getUniqueId();
        return open || (owner != null && owner.equals(uuid))
                || (memberAccess.containsKey(uuid) && memberAccess.get(uuid));
    }

    public List<Player> getPlayersInMine() {
        List<Player> result = new ArrayList<>();

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (mineCuboid.contains(onlinePlayer))
                result.add(onlinePlayer);
        }

        return result;
    }

}