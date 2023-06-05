package gg.convict.prison.pickaxe.enchant.impl.minecrate;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.profile.ProfileModule;
import gg.convict.prison.pickaxe.PickaxeData;
import gg.convict.prison.pickaxe.PickaxeModule;
import gg.convict.prison.pickaxe.enchant.Enchant;
import gg.convict.prison.privatemine.Mine;
import gg.convict.prison.privatemine.MineModule;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.hydrapvp.libraries.utils.CC;

import java.util.concurrent.TimeUnit;

public class CrateFinderEnchant extends Enchant {

    public static final String ENCHANT_ID = "crate_finder";
    public static final String COOLDOWN_METADATA = "crate_cooldown";

    public CrateFinderEnchant() {
        super(ENCHANT_ID, "Crate Finder");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Location blockLocation = event.getBlock().getLocation();
        Mine mine = MineModule.get().getHandler().getMine(blockLocation);

        if (!mine.getMineCuboid().contains(blockLocation))
            return;

        Player player = event.getPlayer();
        PickaxeData data = PickaxeModule.get().getHandler()
                .getData(player.getItemInHand());

        if (data == null || !data.hasEnchant(ENCHANT_ID) || hasCooldown(player))
            return;

        // todo chance

        ProfileModule.get().getMinecrateHandler().give(player, 1);

        player.sendMessage(CC.format(
                "%sYou have found a &bMine Crate&f!",
                PrisonPlugin.PREFIX
        ));

        player.setMetadata(
                COOLDOWN_METADATA,
                new FixedMetadataValue(PrisonPlugin.get(), true)
        );

        PrisonPlugin.EXECUTOR.schedule(() ->
                player.removeMetadata(COOLDOWN_METADATA, PrisonPlugin.get()),
                30L,
                TimeUnit.SECONDS
        );
    }

    private boolean hasCooldown(Player player) {
        return player.hasMetadata(COOLDOWN_METADATA);
    }

}