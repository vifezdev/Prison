package gg.convict.prison.privatemine.grid.command;

import com.sk89q.worldedit.Vector;
import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.privatemine.grid.runnable.MineRunnable;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;

public class GridTeleportCommand {

    @Command(names = "grid teleport",
            permission = "prison.grid.teleport",
            description = "Teleport to the main grid location",
            playerOnly = true)
    public void execute(Player player) {
        MineHandler handler = MineModule.get().getHandler();
        Vector startingPoint = MineRunnable.STARTING_GRID_POINT;

        player.teleport(new Location(
                handler.getMineWorld(),
                startingPoint.getBlockX(),
                startingPoint.getBlockY(),
                startingPoint.getBlockZ()
        ));
    }

}