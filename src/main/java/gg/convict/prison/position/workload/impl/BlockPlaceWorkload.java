package gg.convict.prison.position.workload.impl;

import gg.convict.prison.position.workload.Workload;
import lombok.RequiredArgsConstructor;
import net.minecraft.server.v1_8_R3.Block;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import java.util.UUID;

@RequiredArgsConstructor
public class BlockPlaceWorkload implements Workload {

    private final UUID worldUuid;
    private final int blockX;
    private final int blockY;
    private final int blockZ;
    private final Material material;

    @Override
    public void compute() {
        World world = Bukkit.getWorld(worldUuid);

        if (world != null)
            setBlockInNativeChunk(world, blockX, blockY, blockZ, material.getId(), (byte) 0);
    }

    public void setBlockInNativeChunk(World world, int x, int y, int z, int blockId, byte data) {
        net.minecraft.server.v1_8_R3.World nmsWorld = ((CraftWorld) world).getHandle();
        IBlockData blockData = Block.getByCombinedId(blockId + (data << 12));

        nmsWorld.setTypeAndData(new BlockPosition(x, y, z), blockData, 2);
    }

}