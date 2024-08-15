package gg.convict.prison.privatemine.command;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.privatemine.menu.MineManageMenu;
import gg.convict.prison.privatemine.menu.create.MineCreateMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class PrivateMineCommand {

    private final MineModule module;

    @Command(names = {"pmine", "privatemine", "mine"},
            description = "Manage your private mine",
            playerOnly = true)
    public void execute(Player player) {
        Mine mine = module.getHandler().getMine(player);

        if (mine == null) {
            new MineCreateMenu().openMenu(player);
            return;
        }

        new MineManageMenu(mine).openMenu(player);
    }

}