package gg.convict.prison.privatemine.listener;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import lol.vera.veraspigot.util.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.github.paperspigot.Title;

@RequiredArgsConstructor
public class MineListener implements Listener {

    private final MineModule module;

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockBreak(BlockBreakEvent event) {
        MineHandler handler = module.getHandler();
        Mine mine = handler.getMine(event.getBlock().getLocation());

        if (mine == null)
            return;

        Player player = event.getPlayer();
        double airPercentage = mine.getAirPercentage();

        if (airPercentage >= handler.getMineResetThreshold()) {
            mine.resetBlocks();
            player.teleport(mine.getCenterLocation());

            player.sendTitle(new Title(
                    CC.translate("&b&lMINE RESET"),
                    CC.translate("&fYour mine has been reset"),
                    20, 80, 20
            ));
        }
    }

}