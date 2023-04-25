package gg.convict.prison.privatemine;

import gg.convict.prison.privatemine.generator.VoidMineGenerator;
import gg.convict.prison.privatemine.grid.MineGrid;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.hydrapvp.libraries.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MineHandler implements StaticConfiguration {

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

}