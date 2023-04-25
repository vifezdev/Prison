package gg.convict.prison.privatemine.grid;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.privatemine.runnable.IslandRunnable;
import lombok.Data;

import java.util.function.Consumer;

@Data
public class MineGrid {

    private boolean canPaste = true;
    private int index = 0;

    public void copySchematics(int amount, Consumer<Integer> consumer) {
        if (!canPaste)
            return;

        new IslandRunnable(amount, consumer).runTaskTimer(
                PrisonPlugin.get(), 8L, 8L);
    }

    public void incrementIndex() {
        index += 1;
    }

}