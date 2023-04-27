package gg.convict.prison.position;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.position.workload.impl.BlockPlaceWorkload;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.cuboid.Cuboid;
import org.hydrapvp.libraries.utils.CC;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PositionCommand {

    private final Map<UUID, PositionData> dataMap = new HashMap<>();

    @Command(names = "position",
            permission = "op",
            playerOnly = true)
    public void execute(Player player, @Param(name = "position") int position) {
        if (position > 3) {
            player.sendMessage(CC.RED + "Position must be 3 or lower.");
            return;
        }

        Location location = player.getLocation();
        switch (position) {
            case 1:
                update(player, location, true);
                break;
            case 2:
                update(player, location, false);
                break;
            case 3:
                paste(player);
                break;
        }
    }

    private void paste(Player player) {
        UUID uuid = player.getUniqueId();
        if (!dataMap.containsKey(uuid)) {
            player.sendMessage(ChatColor.RED + "You need to set a position.");
            return;
        }

        PositionData positionData = dataMap.get(uuid);
        if (positionData.getFirstLocation() == null
                || positionData.getSecondLocation() == null) {
            player.sendMessage(ChatColor.RED + "Both locations need to be set.");
            return;
        }

        Cuboid cuboid = new Cuboid(
                positionData.getFirstLocation(),
                positionData.getSecondLocation()
        );

        PrisonPlugin plugin = PrisonPlugin.get();
        for (Block block : cuboid) {
            plugin.getWorkloadRunnable().register(new BlockPlaceWorkload(
                    cuboid.getWorld().getUID(), block.getX(), block.getY(), block.getZ(), Material.STONE));
        }
    }

    public void update(Player player, Location location, boolean first) {
        UUID uniqueId = player.getUniqueId();
        if (!dataMap.containsKey(uniqueId))
            dataMap.put(uniqueId, new PositionData());

        PositionData positionData = dataMap.get(uniqueId);

        if (first)
            positionData.setFirstLocation(location);
        else
            positionData.setSecondLocation(location);

        player.sendMessage(String.format(
                "Set the %s location to (%s, %s, %s)",
                first ? "first" : "second",
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
        ));
    }

}