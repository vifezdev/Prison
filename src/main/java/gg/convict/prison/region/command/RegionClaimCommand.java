package gg.convict.prison.region.command;

import gg.convict.prison.region.Region;
import gg.convict.prison.region.RegionModule;
import gg.convict.prison.region.claimwand.ClaimWandHandler;
import gg.convict.prison.region.claimwand.selection.ClaimSelection;
import gg.convict.prison.util.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;

@RequiredArgsConstructor
public class RegionClaimCommand {

    private final RegionModule module;

    @Command(names = "region claim",
            permission = "prison.region.claim",
            description = "Claim land for a region",
            playerOnly = true)
    public void execute(Player player,
                        @Param(name = "region") Region region) {
        ClaimWandHandler claimWandHandler = module.getClaimWandHandler();

        boolean added = player.getInventory()
                .addItem(claimWandHandler.getClaimWand().toItemStack())
                .values()
                .isEmpty();

        if (added)
            player.sendMessage(CC.GREEN + "A claiming wand was added to your inventory.");
        else
            player.sendMessage(CC.RED + "A claiming wand was not added to your inventory because it is full.");

        claimWandHandler.createSelection(player, new ClaimSelection(
                region,
                player.getUniqueId(),
                player.getWorld().getName()
        ));
    }

}