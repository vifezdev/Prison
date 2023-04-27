package gg.convict.prison.privatemine.grid;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.grid.runnable.MineRunnable;
import lombok.Data;

import java.io.File;
import java.util.function.Consumer;

@Data
public class MineGrid {

    private boolean canPaste = true;
    private int index = 0;

    public void copySchematics(SchematicType type, int amount, Consumer<Integer> consumer) {
        if (!canPaste)
            return;

        File file = new File(MineSchematic.SCHEMATIC_DIR, type.getFileName());
        if (!file.exists()) {
            System.err.println("Could not paste schematic: " + type.getFileName() + " was not found.");
            return;
        }

        new MineRunnable(type, amount, consumer).runTaskTimer(
                PrisonPlugin.get(), 8L, 8L);
    }

    public void incrementIndex() {
        index += 1;
    }

}