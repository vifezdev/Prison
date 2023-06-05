package gg.convict.prison.warp.command;

import gg.convict.prison.PrisonPlugin;
import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import gg.convict.prison.warp.menu.WarpsMenu;
import lol.vera.veraspigot.util.CC;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.hydrapvp.libraries.command.annotation.Command;
import org.hydrapvp.libraries.command.annotation.Param;

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
        if (warp.getLocation() == null) {
            player.sendMessage(CC.format(
                    "%s&cNo location set for warp %s&c.",
                    PrisonPlugin.PREFIX,
                    warp.getDisplayName()
            ));
            return;
        }

        player.teleport(warp.getLocation());
        player.sendMessage(CC.format(
                "%s&fTeleported to warp %s&f.",
                PrisonPlugin.PREFIX,
                warp.getDisplayName()
        ));
    }

}