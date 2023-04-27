package gg.convict.prison.position;

import lite.LiteEdit;
import lite.LiteRegion;
import net.minecraft.server.v1_8_R3.IBlockData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.cuboid.Cuboid;
import org.hydrapvp.libraries.utils.CC;
import org.jetbrains.annotations.NotNull;

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

        LiteEdit.fill(new LiteRegion(cuboid), new LiteEdit.FillHandler() {

            @NotNull
            @Override
            public IBlockData getData(@NotNull Material material, int data) {
                return LiteEdit.FillHandler.super.getData(material, data);
            }

            @Override
            public IBlockData getBlock(int x, int y, int z) {
                return getData(Material.STONE, 0);
            }

        }, new LiteEdit.ProgressCallback() {
            @Override
            public void start(int totalChunks) {
                player.sendMessage("Starting lite edit with " + totalChunks + " chunks.");
            }

            @Override
            public void progress(int chunks, int totalChunks) {
                player.sendMessage("Progress: " + chunks + "/" + totalChunks);
            }

            @Override
            public void end(int totalChunks) {
                player.sendMessage("Lite edit ended with " + totalChunks);
            }
        });
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