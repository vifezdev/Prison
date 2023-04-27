package gg.convict.prison.privatemine;

import lombok.Data;
import net.minecraft.server.v1_8_R3.IBlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;
import org.hydrapvp.libraries.cuboid.Cuboid;
import org.hydrapvp.libraries.lite.LiteEdit;
import org.hydrapvp.libraries.lite.LiteRegion;
import org.hydrapvp.libraries.lite.callback.VoidProgressCallback;
import org.hydrapvp.libraries.lite.handler.FillHandler;

import java.util.UUID;

@Data
public class Mine {

    private UUID owner;
    private Cuboid cuboid;
    private Cuboid mineCuboid;
    private LocationConfig spawnLocation;

    public void resetBlocks() {
        LiteRegion liteRegion = new LiteRegion(mineCuboid);
        LiteEdit.fill(liteRegion, new FillHandler() {

            @Override
            public IBlockData getBlock(int i, int i1, int i2) {
                return getData(Material.STONE, 0);
            }

        }, new VoidProgressCallback());
    }

}