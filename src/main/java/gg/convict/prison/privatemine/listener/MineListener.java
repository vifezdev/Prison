package gg.convict.prison.privatemine.listener;

import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineHandler;
import gg.convict.prison.privatemine.MineModule;
import gg.convict.prison.profile.Profile;
import gg.convict.prison.profile.ProfileModule;
import lol.vera.veraspigot.util.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.github.paperspigot.Title;

@RequiredArgsConstructor
public class MineListener implements Listener {

    private final MineModule module;

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) {
        MineHandler handler = module.getHandler();
        Mine mine = handler.getMine(event.getBlock().getLocation());

        if (mine == null)
            return;

        Profile profile = ProfileModule.get()
                .getProfileHandler().getProfile(event.getPlayer());

        for (ItemStack drop : event.getBlock().getDrops())
            profile.addBalance(100000); // todo this is testing

        event.getBlock().setType(Material.AIR);

        double airPercentage = mine.getAirPercentage();
        if (airPercentage < handler.getMineResetThreshold())
            return;

        mine.resetBlocks();
        mine.getPlayersInMine().forEach(inMine -> {
            inMine.teleport(mine.getCenterLocation());

            inMine.sendTitle(new Title(
                    CC.translate("&b&lMINE RESET"),
                    CC.translate("&fThe mine you were in has been reset"),
                    20, 80, 20
            ));
        });
    }

}