package gg.convict.prison.warp.command;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.config.PrisonBranding;
import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import gg.convict.prison.warp.menu.WarpsMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;
import org.hydrapvp.libraries.utils.CC;

@RequiredArgsConstructor
public class WarpCommand {

    public static final String UNKNOWN_WARP = "43924230SD:SD";
    private final WarpModule module;

    @Command(names = {"warp", "warps"},
            description = "Teleport to a warp",
            playerOnly = true)
    public void execute(Player player, @Param(name = "warp", defaultValue = UNKNOWN_WARP) String warpName) {
        if (warpName.equals(UNKNOWN_WARP)) {
            new WarpsMenu().openMenu(player);
            return;
        }

        Warp warp = module.getWarp(warpName);
        if (warp == null) {
            player.sendMessage(org.hydrapvp.libraries.utils.CC.format(
                    "&cA warp with the name &b%s&c does not exist.",
                    warpName));
            return;
        }

        if (warp.getLocation() == null) {
            player.sendMessage(CC.format(
                    "%s&cNo location set for warp %s&c.",
                    PrisonBranding.get().getPrefix(),
                    warp.getDisplayName()
            ));
            return;
        }

        player.teleport(warp.getLocation());
        player.sendMessage(CC.format(
                "%s&fTeleported to warp %s&f.",
                PrisonBranding.get().getPrefix(),
                warp.getDisplayName()
        ));
    }

}