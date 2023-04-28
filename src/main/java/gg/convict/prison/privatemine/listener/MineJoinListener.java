package gg.convict.prison.privatemine.listener;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class MineJoinListener implements Listener {

    private final MineModule module;

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Mine mine = module.getHandler().getMine(player.getLocation());

        if (mine != null)
            mine.teleport(player);
    }

}