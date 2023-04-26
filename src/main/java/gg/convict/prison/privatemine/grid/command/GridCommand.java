package gg.convict.prison.privatemine.grid.command;

import gg.convict.prison.privatemine.grid.menu.GridAdminMenu;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;

public class GridCommand {

    @Command(names = "grid manage",
            permission = "prison.grid.manage",
            description = "Manage the mine grid",
            playerOnly = true)
    public void execute(Player player) {
        GridAdminMenu.INSTANCE.openMenu(player);
    }

}