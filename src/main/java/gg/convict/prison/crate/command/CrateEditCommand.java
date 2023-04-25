package gg.convict.prison.crate.command;

import gg.convict.prison.crate.Crate;
import gg.convict.prison.crate.menu.CrateEditMenu;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;

public class CrateEditCommand {

    @Command(names = "crate edit",
            permission = "prison.crate.edit",
            description = "Edit a crate",
            playerOnly = true)
    public void execute(Player player, @Param(name = "crate") Crate crate) {
        new CrateEditMenu(crate).openMenu(player);
    }

}