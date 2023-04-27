package gg.convict.prison.privatemine.allocation;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.github.paperspigot.Title;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.configuration.defaults.LocationConfig;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class MineAllocateCommand {

    private final MineModule module;

    @Command(names = "testallocate",
            permission = "op",
            playerOnly = true)
    public void execute(Player player) {
        MineHandler handler = module.getHandler();

        if (handler.hasMine(player)) {
            player.sendMessage(CC.RED + "You already have a private mine.");
            return;
        }

        Mine mine = handler.allocateMine(player);
        LocationConfig spawnLocation = mine.getSpawnLocation();

        player.teleport(spawnLocation.getLocation());
        player.sendTitle(new Title(
                CC.translate("&a&lMINE CREATED"),
                CC.translate("&fTeleported you to your mine"),
                20, 80, 20
        ));
    }

}