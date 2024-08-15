package gg.convict.prison.warp.command;

import gg.convict.prison.warp.Warp;
import gg.convict.prison.warp.WarpModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import gg.convict.prison.util.command.annotation.Command;
import gg.convict.prison.util.command.annotation.Param;
import gg.convict.prison.util.CC;

@RequiredArgsConstructor
public class WarpSetLocationCommand {

    private final WarpModule module;

    @Command(names = "warp setlocation",
            description = "Set the location of a warp",
            permission = "prison.warp.setlocation",
            playerOnly = true)
    public void execute(Player player, @Param(name = "warp") Warp warp) {
        Location location = player.getLocation();

        warp.setLocation(location);
        warp.save();

        player.sendMessage(CC.format(
                "&fSet the location of &b%s&f to &b%s&f, &b%s&f, &b%s&f.",
                warp.getDisplayName(),
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
        ));
    }

}