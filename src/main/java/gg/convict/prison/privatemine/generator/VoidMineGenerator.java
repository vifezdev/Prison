package gg.convict.prison.privatemine.generator;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class VoidMineGenerator extends ChunkGenerator {

    @Override
    public byte[] generate(World world, Random random, int x, int z) {
        return new byte[32768];
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return world.getSpawnLocation();
    }

}