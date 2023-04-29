package gg.convict.prison.privatemine.listener;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

@RequiredArgsConstructor
public class MineListener implements Listener {

    private final MineModule module;

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        MineHandler handler = module.getHandler();
        Mine mine = handler.getMine(event.getBlock().getLocation());

        if (mine == null
                || !mine.canMine(player, event.getBlock().getLocation())
                || player.getItemInHand() == null
                || player.getItemInHand().getType() != Material.DIAMOND_PICKAXE)
            return;

        Profile profile = ProfileModule.get()
                .getProfileHandler().getProfile(player);

        profile.addBalance(100000); // todo debug value
        event.getBlock().setType(Material.AIR);

        mine.updateAirPercentage();

        double airPercentage = mine.getAirPercentage();
        if (airPercentage < handler.getMineResetThreshold())
            return;

        mine.resetBlocks(true);
    }

}