package gg.convict.prison.privatemine.grid;

import com.boydti.fawe.Fawe;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.SchematicFormat;
import gg.convict.prison.config.LocationConfig;
import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.privatemine.grid.runnable.MineRunnable;
import gg.convict.prison.privatemine.util.AngleUtil;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.plugin.java.JavaPlugin;
import org.hydrapvp.libraries.cuboid.Cuboid;

import java.io.File;

@Data
public class MineSchematic {

    public static MineSchematic INSTANCE = new MineSchematic();

    private static final File SCHEMATIC_DIR = new File(
            JavaPlugin.getPlugin(WorldEditPlugin.class).getDataFolder(),
            "schematics"
    );

    private final World world;
    private final EditSession editSession;

    public MineSchematic() {
        this.world = MineModule.get().getHandler().getMineWorld();
        this.editSession = Fawe.get().getWorldEdit().getEditSessionFactory()
                .getEditSession(new BukkitWorld(world), Integer.MAX_VALUE);
    }

    public Mine paste(SchematicType type, int startX, int startZ) throws Exception {
        CuboidClipboard clipboard = SchematicFormat.MCEDIT.load(getSchematic(type));
        Vector pasteVector = new Vector(startX, MineRunnable.STARTING_GRID_POINT.getY(), startZ);

        clipboard.setOffset(new Vector(0, 0, 0));
        clipboard.paste(editSession, pasteVector, true);
        Cuboid cuboid = new Cuboid(
                fromVector(pasteVector, world),
                fromVector(pasteVector.add(clipboard.getSize()), world)
        );

        Mine mine = new Mine();
        mine.setOwner(null);
        mine.setCuboid(cuboid);

        for (Block block : cuboid) {
            if (block.getType() != Material.SKULL)
                continue;

            Skull skull = (Skull) block.getState();
            Location clone = block.getLocation().clone();

            clone.setYaw(AngleUtil.parseFace(skull.getRotation()));
            clone.add(0.5, 0, 0.5);

            block.setType(Material.AIR);
            mine.setSpawnLocation(new LocationConfig(clone));
        }

        return mine;
    }

    public File getSchematic(SchematicType type) {
        return new File(SCHEMATIC_DIR, type.getFinalName());
    }

    public static Location fromVector(Vector vector, World world) {
        return new Location(
                world,
                vector.getX(),
                vector.getY(),
                vector.getZ()
        );
    }

}