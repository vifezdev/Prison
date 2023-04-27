package gg.convict.prison.privatemine.grid.runnable;

import com.sk89q.worldedit.Vector;
import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.privatemine.grid.MineGrid;
import gg.convict.prison.privatemine.grid.MineSchematic;
import gg.convict.prison.privatemine.grid.SchematicType;
import lombok.RequiredArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class MineRunnable extends BukkitRunnable {

    public static final Vector STARTING_GRID_POINT
            = new Vector(1500, 100, 1500);

    private static final int GRID_SPACING = 500;

    private final int amount;
    private final MineGrid grid;
    private final SchematicType type;
    private final Consumer<Integer> consumer;

    private int count = 0;

    public MineRunnable(SchematicType type, int amount, Consumer<Integer> consumer) {
        this.type = type;
        this.amount = amount;
        this.consumer = consumer;
        this.grid = MineModule.get().getHandler().getMineGrid();
    }

    @Override
    public void run() {
        grid.setCanPaste(false);

        try {
            insertMine(type, true);
            count += 1;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (count >= amount) {
            consumer.accept(count);
            grid.setCanPaste(true);

            cancel();
        }
    }

    public void insertMine(SchematicType type, boolean addToList) throws Exception {
        int index = grid.getIndex();
        int copy = index + 1;

        int x = STARTING_GRID_POINT.getBlockX() + (GRID_SPACING + index);
        int z = STARTING_GRID_POINT.getBlockZ() + (GRID_SPACING * copy);

        Mine mine = MineSchematic.INSTANCE.paste(type, x, z);
        mine.resetBlocks();

        grid.incrementIndex();

        if (addToList) {
            MineModule mineModule = MineModule.get();
            mineModule.getHandler().getFreeMines().add(mine);
            mineModule.saveConfig();
        }
    }

}