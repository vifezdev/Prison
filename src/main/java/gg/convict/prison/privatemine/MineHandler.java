package gg.convict.prison.privatemine;

import gg.convict.prison.privatemine.generator.VoidMineGenerator;
import gg.convict.prison.privatemine.grid.MineGrid;
import gg.convict.prison.privatemine.grid.SchematicType;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.configuration.StaticConfiguration;
import org.hydrapvp.libraries.utils.CC;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MineHandler implements StaticConfiguration {

    private final double mineResetThreshold = 50.0;
    private final List<Mine> usedMines = new ArrayList<>();
    private final List<Mine> freeMines = new ArrayList<>();

    private transient final World mineWorld;
    private transient final MineGrid mineGrid = new MineGrid();

    public MineHandler() {
        this.mineWorld = Bukkit.createWorld(new WorldCreator("mines")
                .environment(World.Environment.NORMAL)
                .generator(new VoidMineGenerator())
        );
    }
    
    public Mine allocateMine(Player player, SchematicType type) {
        Mine freeMine = getFreeMine(type);
        if (freeMine == null) {
            player.sendMessage(CC.RED + "No mines found, creating one for you.");
            return null;
        }

        freeMine.setOwner(player.getUniqueId());

        usedMines.add(freeMine);
        freeMines.remove(freeMine);

        MineModule.get().saveConfig();
        return freeMine;
    }

    public Mine getFreeMine(SchematicType type) {
        for (Mine freeMine : freeMines) {
            if (freeMine.getType() == type)
                return freeMine;
        }

        return null;
    }

    public boolean hasMine(Player player) {
        return getMine(player) != null;
    }

    public int getMineCount(SchematicType type) {
        int count = 0;

        for (Mine freeMine : freeMines) {
            if (freeMine.getType() == type)
                count += 1;
        }

        return count;
    }

    public Mine getMine(Location location) {
        for (Mine usedMine : usedMines) {
            if (usedMine.getCuboid().contains(location))
                return usedMine;
        }

        for (Mine freeMine : freeMines) {
            if (freeMine.getCuboid().contains(location))
                return freeMine;
        }

        return null;
    }

    public Mine getMine(Player player) {
        for (Mine usedMine : usedMines) {
            if (usedMine.getOwner().equals(player.getUniqueId()))
                return usedMine;
        }

        return null;
    }

}